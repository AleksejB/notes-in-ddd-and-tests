package com.aleksejb.ui.notes

import com.aleksejb.core.domain.model.NoteType

sealed interface NotesEvent {
    data class OnNoteTypeSelected(val noteType: NoteType): NotesEvent
    object OnNewNoteClicked: NotesEvent
    data class OnNoteClicked(val noteId: Int): NotesEvent
}