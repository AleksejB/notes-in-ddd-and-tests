package com.aleksejb.ui.checkbox.note

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aleksejb.core.domain.model.CheckboxItem
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.ui.R
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CheckboxNoteScreen(
    noteId: Int?,
    viewModel: CheckboxNoteViewModel = koinViewModel(parameters = { parametersOf(noteId) })
) {
    val state by viewModel.state.collectAsState()

    DisposableEffect(key1 = Unit) { onDispose { viewModel.onDispose() } }

    CheckboxNoteScreenContent(state = state, eventHandler = viewModel::postEvent)
}

@Composable
private fun CheckboxNoteScreenContent(
    state: CheckboxNoteState,
    eventHandler: (CheckboxNoteEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(horizontal = dimensionResource(id = R.dimen.medium_100))
    ) {
        TitleTextField(state, eventHandler)

        LazyColumn {
            items(state.items) { checkboxItem ->
                CheckboxItem(
                    checkboxItem = checkboxItem,
                    onCheckboxClick = { eventHandler(CheckboxNoteEvent.OnItemChecked(checkboxItem.id)) },
                    onItemNameChanged = { eventHandler(CheckboxNoteEvent.OnItemNameChanged(checkboxItem.id, it)) }
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Gray),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { eventHandler(CheckboxNoteEvent.OnNewItemClicked) },
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun CheckboxItem(
    checkboxItem: CheckboxItem,
    onCheckboxClick: () -> Unit,
    onItemNameChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = checkboxItem.isComplete,
            onCheckedChange = { onCheckboxClick() }
        )

        TextField(
            value = checkboxItem.itemName,
            onValueChange = { onItemNameChanged(it) }
        )
    }
}

@Composable
private fun TitleTextField(
    state: CheckboxNoteState,
    eventHandler: (CheckboxNoteEvent) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.medium_100)),
        value = state.title,
        onValueChange = { eventHandler(CheckboxNoteEvent.OnTitleChanged(it)) }
    )
}