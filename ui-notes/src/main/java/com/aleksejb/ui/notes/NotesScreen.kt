package com.aleksejb.ui.notes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.core.domain.util.capitalise
import com.aleksejb.core.domain.util.capitalizeFullCapsString
import org.koin.androidx.compose.koinViewModel
import com.aleksejb.core.ui.R

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

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