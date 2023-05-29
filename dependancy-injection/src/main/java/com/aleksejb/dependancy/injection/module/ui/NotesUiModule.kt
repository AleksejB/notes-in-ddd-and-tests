package com.aleksejb.dependancy.injection.module.ui

import com.aleksejb.ui.notes.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesUiModule = module {
    viewModel { NotesViewModel() }
}