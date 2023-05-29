package com.aleksejb.ui.note

import com.aleksejb.core.domain.model.Note
import com.aleksejb.core.domain.model.NoteBasicInfo
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NoteViewModel(
    private val noteBasicInfo: NoteBasicInfo,
//    private val getNodeByIdUseCase
): MVIViewModel<NoteState, NoteEvent, NoteEffect>() {

    override val _state = MutableStateFlow(NoteState(null))

    override fun handleEvent(event: NoteEvent) {
        when (event) {
            else -> Unit
        }
    }

    //Ok, so we know which note type we are dealing with when we navigate to this screen
    // We could pass the noteId and noteType, then depending on note type we get note by id


}