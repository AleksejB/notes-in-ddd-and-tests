package com.aleksejb.ui.notes

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class NotesState(
    val currentTab: NoteType = NoteType.TEXT,
    val checkboxNotesPagingData: Flow<PagingData<CheckboxNote>> = flowOf(),
    val textNotesPagingData: Flow<PagingData<TextNote>> = flowOf(),
    val imageNotesPagingData: Flow<PagingData<ImageNote>> = flowOf()
)