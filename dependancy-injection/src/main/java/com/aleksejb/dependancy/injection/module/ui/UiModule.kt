package com.aleksejb.dependancy.injection.module.ui

import com.aleksejb.ui.checkbox.note.CheckboxNoteViewModel
import com.aleksejb.ui.image.note.ImageNoteViewModel
import com.aleksejb.ui.notes.NotesViewModel
import com.aleksejb.ui.text.note.TextNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { NotesViewModel(get(), get(), get()) }

    viewModel { (noteId: Int?) -> TextNoteViewModel(noteId, get(), get(), get()) }
    viewModel { (noteId: Int?) -> CheckboxNoteViewModel(noteId, get(), get(), get()) }
    viewModel { (noteId: Int?) -> ImageNoteViewModel(noteId, get(), get(), get()) }
}