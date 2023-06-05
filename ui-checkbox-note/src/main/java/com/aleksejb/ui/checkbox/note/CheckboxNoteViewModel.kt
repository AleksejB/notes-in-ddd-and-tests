package com.aleksejb.ui.checkbox.note

import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CheckboxNoteViewModel(

): MVIViewModel<CheckboxNoteState, CheckboxNoteEvent, CheckboxNoteEffect>() {

    override val _state = MutableStateFlow(CheckboxNoteState())

    override fun handleEvent(event: CheckboxNoteEvent) {
        when (event) {
            else -> {}
        }
    }

}