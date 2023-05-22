package com.aleksejb.ui.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen)),
            text = stringResource(R.string.notes)
        )
    }
}