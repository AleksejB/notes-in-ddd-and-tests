package com.aleksejb.ui.notes

import com.aleksejb.core.domain.model.NoteBasicInfo

sealed interface NotesEffect {
    data class NavigateToNewNote(val noteBasicInfo: NoteBasicInfo): NotesEffect
    data class NavigateToNote(val noteId: Int): NotesEffect
}