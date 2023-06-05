package com.aleksejb.ui.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.aleksejb.core.domain.model.Note
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.core.domain.model.TextNote
import com.aleksejb.core.domain.util.Constants.NON_EXISTENT_NOTE_ID
import com.aleksejb.core.domain.util.capitalise
import com.aleksejb.core.domain.util.capitalizeFullCapsString
import org.koin.androidx.compose.koinViewModel
import com.aleksejb.core.ui.R
import com.aleksejb.core.ui.components.NoteDivider
import com.aleksejb.ui.navigation.Navigator
import com.aleksejb.ui.navigation.graph.NotesGraph

@Composable
fun NotesScreen(
    navigator: Navigator<NotesGraph>,
    viewModel: NotesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is NotesEffect.NavigateToNewNote -> {
                    navigator.navigateToAppropriateNoteType(state.currentTab, NON_EXISTENT_NOTE_ID)
                }
                is NotesEffect.NavigateToNote -> {
                    navigator.navigateToAppropriateNoteType(state.currentTab, effect.noteId)
                }
            }
        }
    }

    NotesScreenContent(
        state = state,
        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun NotesScreenContent(state: NotesState, eventHandler: (NotesEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TitleText()

            NoteTypeSelection(
                selectedNoteTab = state.currentTab
            ) { eventHandler(NotesEvent.OnNoteTypeSelected(it)) }

            when (state.currentTab) {
                NoteType.TEXT -> {
                    val notes = state.textNotesPagingData.collectAsLazyPagingItems()

                    NotesLazyColumn(items = notes) {
                        TextNoteItem(textNote = it) { eventHandler(NotesEvent.OnNoteClicked(it)) }
                    }
                }
                NoteType.CHECKBOX -> {
                    val notes = state.checkboxNotesPagingData.collectAsLazyPagingItems()

                    NotesLazyColumn(items = notes) {
                        ImageAndCheckboxNoteItem(title = it.title) { eventHandler(NotesEvent.OnNoteClicked(it.id)) }
                    }
                }
                NoteType.IMAGE -> {

                }
            }
        }

        NewNoteFAB(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) { eventHandler(NotesEvent.OnNewNoteClicked) }
    }
}

@Composable
private fun NewNoteFAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.medium_100))
            .navigationBarsPadding(),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null
        )
    }
}

@Composable
private fun NoteTypeSelection(
    selectedNoteTab: NoteType,
    onClick: (NoteType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.medium_100))
    ) {
        NoteType.values().forEach {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick(it) },
                color = if (selectedNoteTab == it) Color.Black else Color.White
            ) {
                NoteTypeNameText(selectedNoteType = selectedNoteTab, noteType = it)
            }
        }
    }
}

@Composable
private fun NoteTypeNameText(
    selectedNoteType: NoteType,
    noteType: NoteType
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.small_50)),
        text = noteType.name.capitalizeFullCapsString().capitalise(),
        color = if (selectedNoteType == noteType) Color.White else Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TitleText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.medium_100))
            .statusBarsPadding(),
        text = stringResource(R.string.notes),
        textAlign = TextAlign.Center,
        fontSize = 32.sp
    )
}

private fun Navigator<NotesGraph>.navigateToAppropriateNoteType(noteType: NoteType, noteId: Int) {
    when (noteType) {
        NoteType.TEXT -> navigate(NotesGraph.TextNote(noteId))
        NoteType.IMAGE -> navigate(NotesGraph.ImageNote(noteId))
        NoteType.CHECKBOX -> navigate(NotesGraph.CheckboxNote(noteId))
    }
}

@Composable
private fun TextNoteItem(textNote: TextNote, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(textNote.id!!) }
    ) {
        NoteItemTitleText(title = textNote.title)

        TextNoteBodyPreviewText(textNote)

        NoteDivider()
    }
}

@Composable
private fun NoteItemTitleText(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun TextNoteBodyPreviewText(textNote: TextNote) {
    Text(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.small_100)),
        text = textNote.text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ImageAndCheckboxNoteItem(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        NoteItemTitleText(title = title)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_100)))

        NoteDivider()
    }
}

@Composable
private fun <T : Note> NotesLazyColumn(
    items: LazyPagingItems<T>,
    itemContent: @Composable ((T) -> Unit)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.medium_100))
    ) {
        itemsIndexed(items) { index, note ->
            note?.let { itemContent(note) }
        }
    }
}

