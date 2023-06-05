package com.aleksejb.dependancy.injection.module.ui

import com.aleksejb.ui.notes.NotesViewModel
import com.aleksejb.ui.text.note.TextNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesUiModule = module {
    viewModel { NotesViewModel(get(), get()) }
    viewModel { params -> TextNoteViewModel(params.get(), get(), get()) }
}