package com.aleksejb.ui.notes

import com.aleksejb.core.domain.usecase.GetCheckboxNotesPagerUseCase
import com.aleksejb.core.domain.usecase.GetImageNotesPagerUseCase
import com.aleksejb.core.domain.usecase.GetTextNotesPagerUseCase
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NotesViewModel(
    private val getCheckboxNotesPagerUseCase: GetCheckboxNotesPagerUseCase,
    private val getTextNotesPagerUseCase: GetTextNotesPagerUseCase,
    private val getImageNotesPagerUseCase: GetImageNotesPagerUseCase
): MVIViewModel<NotesState, NotesEvent, NotesEffect>() {

    override val _state = MutableStateFlow(NotesState())

    override fun handleEvent(event: NotesEvent) {
        when (event) {
            else -> {}
        }
    }

}