package com.aleksejb.ui.notes

import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.core.domain.usecase.GetCheckboxNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.GetTextNotesPagingDataUseCase
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NotesViewModel(
    private val getCheckboxNotesPagingDataUseCase: GetCheckboxNotesPagingDataUseCase,
    private val getTextNotesPagingDataUseCase: GetTextNotesPagingDataUseCase
//    private val getImageNotesPagerUseCase: GetImageNotesPagerUseCase
): MVIViewModel<NotesState, NotesEvent, NotesEffect>() {

    override val _state = MutableStateFlow(NotesState())

    init { refreshPagers() }

    override fun handleEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.OnNewNoteClicked -> postEffect(NotesEffect.NavigateToNewNote(
                NoteBasicInfo(id = -1, noteType = state.value.currentTab)
            ))
            is NotesEvent.OnNoteTypeSelected -> {
                if (state.value.currentTab == event.noteType) return

                updateState { copy(currentTab = event.noteType) }
            }
            is NotesEvent.OnNoteClicked -> postEffect(NotesEffect.NavigateToNote(event.noteId))
        }
    }

    private fun refreshPagers() {
        refreshTextNotePager()
        refreshCheckboxNotePager()
    }

    private fun refreshTextNotePager() {
        val pager = getTextNotesPagingDataUseCase()
        updateState { copy(textNotesPagingData = pager) }
    }

    private fun refreshCheckboxNotePager() {
        val pager = getCheckboxNotesPagingDataUseCase()
        updateState { copy(checkboxNotesPagingData = pager) }
    }

}