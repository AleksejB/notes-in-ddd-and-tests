package com.aleksejb.ui.checkbox.note

import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.usecase.GetCheckboxNoteFlowUseCase
import com.aleksejb.core.domain.usecase.SaveCheckboxNoteUseCase
import com.aleksejb.core.domain.util.Constants.NON_EXISTENT_NOTE_ID
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CheckboxNoteViewModel(
    private val noteId: Int,
    private val saveCheckboxNoteUseCase: SaveCheckboxNoteUseCase,
    private val getCheckboxNoteUseCase: GetCheckboxNoteFlowUseCase
): MVIViewModel<CheckboxNoteState, CheckboxNoteEvent, CheckboxNoteEffect>() {

    override val _state = MutableStateFlow(CheckboxNoteState())

    init { observeNoteIfExists() }

    override fun handleEvent(event: CheckboxNoteEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun observeNoteIfExists() {
        if (noteId == NON_EXISTENT_NOTE_ID) return

        getCheckboxNoteUseCase.invoke(noteId)
            .filterNotNull()
            .onEach { updateState { copy(title = it.title, items = it.items) } }
            .launchIn(viewModelScope)
    }
}