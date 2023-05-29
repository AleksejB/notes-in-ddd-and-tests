package com.aleksejb.ui.notes.koin

import com.aleksejb.ui.notes.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesUiModule = module {
    viewModel { NotesViewModel() }
}