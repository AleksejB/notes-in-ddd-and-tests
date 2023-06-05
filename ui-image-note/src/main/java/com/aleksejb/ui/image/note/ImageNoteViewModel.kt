package com.aleksejb.ui.image.note

import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ImageNoteViewModel(

): MVIViewModel<ImageNoteState, ImageNoteEvent, ImageNoteEffect>() {

    override val _state = MutableStateFlow(ImageNoteState())

    override fun handleEvent(event: ImageNoteEvent) {
        when (event) {
            else -> {}
        }
    }

}