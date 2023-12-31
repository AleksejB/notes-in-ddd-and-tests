package com.aleksejb.ui.text.note

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.aleksejb.core.ui.R
import com.aleksejb.core.ui.components.BaseNoteScreen

@Composable
fun TextNoteScreen(
    noteId: Int?,
    viewModel: TextNoteViewModel = koinViewModel(parameters = { parametersOf(noteId) })
) {
    val state by viewModel.state.collectAsState()

    DisposableEffect(key1 = Unit) { onDispose { viewModel.onDispose() } }

    TextNoteScreenContent(state = state, eventHandler = viewModel::postEvent)
}

@Composable
private fun TextNoteScreenContent(
    state: TextNoteState,
    eventHandler: (TextNoteEvent) -> Unit
) {
    BaseNoteScreen(
        title = state.title,
        onTitleChanged = { eventHandler(TextNoteEvent.OnTitleChanged(it)) }
    ) {
        TextTextField(state, eventHandler)
    }
}

@Composable
private fun TextTextField(
    state: TextNoteState,
    eventHandler: (TextNoteEvent) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.medium_100)),
        value = state.text,
        onValueChange = { eventHandler(TextNoteEvent.OnTextChanged(it)) }
    )
}

@Composable
private fun TitleTextField(
    state: TextNoteState,
    eventHandler: (TextNoteEvent) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.medium_100)),
        value = state.title,
        onValueChange = { eventHandler(TextNoteEvent.OnTitleChanged(it)) }
    )
}