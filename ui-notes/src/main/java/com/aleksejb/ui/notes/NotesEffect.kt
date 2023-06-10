package com.aleksejb.ui.notes


sealed interface NotesEffect {
    object NavigateToNewNote: NotesEffect
    data class NavigateToNote(val noteId: Int): NotesEffect
}