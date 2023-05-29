package com.aleksejb.ui.note

import androidx.compose.runtime.Composable
import com.aleksejb.core.domain.model.Note
import com.aleksejb.core.domain.model.NoteBasicInfo
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteScreen(
    noteBasicInfo: NoteBasicInfo,
    viewModel: NoteViewModel = koinViewModel(parameters = { parametersOf(noteBasicInfo) })
) {

}