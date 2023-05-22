package com.aleksejb.ui.notes

import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.core.domain.model.TextNote

data class NotesState(
    val currentTab: NoteType = NoteType.TEXT,
    val checkboxNotes: List<CheckboxNote> = emptyList(),
    val textNotes: List<TextNote> = emptyList(),
    val imageNote: List<ImageNote> = emptyList()
)