package com.aleksejb.ui.notes

import android.util.Log
import androidx.paging.map
import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.core.domain.model.NoteType
import com.aleksejb.core.domain.usecase.checkbox.GetCheckboxNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.image.GetImageNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.text.GetTextNotesPagingDataUseCase
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class NotesViewModel(
    private val getCheckboxNotesPagingDataUseCase: GetCheckboxNotesPagingDataUseCase,
    private val getTextNotesPagingDataUseCase: GetTextNotesPagingDataUseCase,
    private val getImageNotesPagingDataUseCase: GetImageNotesPagingDataUseCase
): MVIViewModel<NotesState, NotesEvent, NotesEffect>() {

    override val _state = MutableStateFlow(NotesState())

    init { refreshPagers() }

    override fun handleEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.OnNewNoteClicked -> postEffect(NotesEffect.NavigateToNewNote)
            is NotesEvent.OnNoteTypeSelected -> updateCurrentTab(event.noteType)
            is NotesEvent.OnNoteClicked -> postEffect(NotesEffect.NavigateToNote(event.noteId))
        }
    }

    private fun updateCurrentTab(noteType: NoteType) {
        updateState { copy(currentTab = noteType) }
    }

    private fun refreshPagers() {
        refreshTextNotePager()
        refreshCheckboxNotePager()
        refreshImageNotePager()
    }

    private fun refreshTextNotePager() {
        val pagingData = getTextNotesPagingDataUseCase()
        updateState { copy(textNotesPagingData = pagingData) }
    }

    private fun refreshCheckboxNotePager() {
        val pagingData = getCheckboxNotesPagingDataUseCase()
        updateState { copy(checkboxNotesPagingData = pagingData) }
    }

    private fun refreshImageNotePager() {
        val pagingData = getImageNotesPagingDataUseCase()
        updateState { copy(imageNotesPagingData = pagingData) }
    }

}