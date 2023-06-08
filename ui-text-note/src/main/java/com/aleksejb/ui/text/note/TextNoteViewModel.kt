package com.aleksejb.ui.text.note

import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.model.TextNote
import com.aleksejb.core.domain.usecase.GetTextNoteFlowUseCase
import com.aleksejb.core.domain.usecase.SaveTextNoteUseCase
import com.aleksejb.core.domain.util.Constants.NON_EXISTENT_NOTE_ID
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class TextNoteViewModel(
    private val noteId: Int,
    private val getTextNoteFlowUseCase: GetTextNoteFlowUseCase,
    private val saveTextNoteUseCase: SaveTextNoteUseCase
): MVIViewModel<TextNoteState, TextNoteEvent, TextNoteEffect>() {

    override val _state = MutableStateFlow(TextNoteState())

    init { getNoteIfExists() }

    override fun handleEvent(event: TextNoteEvent) {
        when (event) {
            is TextNoteEvent.OnTitleChanged -> updateTitle(event.input)
            is TextNoteEvent.OnTextChanged -> updateText(event.input)
        }
    }

    fun onDispose() {
        val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        scope.launch {
            if (noteExists()) cancel()

            saveTextNoteUseCase.invoke(
                TextNote(
                    id = null,
                    title = state.value.title,
                    text = state.value.text
                )
            )
        }
    }

    private fun updateText(text: String) {
        updateState { copy(text = text) }
    }

    private fun updateTitle(title: String) {
        updateState { copy(title = title) }
    }

    private fun saveNote(title: String = state.value.title, text: String = state.value.text) {
        viewModelScope.launch {
            saveTextNoteUseCase.invoke(
                TextNote(
                    id = noteId,
                    title = title,
                    text = text
                )
            )
        }
    }

    private fun noteExists() = noteId != NON_EXISTENT_NOTE_ID

    private fun noteDoesNotExist() = noteId == NON_EXISTENT_NOTE_ID

    private fun getNoteIfExists() {
        viewModelScope.launch {
            if (noteDoesNotExist()) cancel()

            getTextNoteFlowUseCase.invoke(noteId)?.let {
                updateState { copy(title = it.title, text = it.text) }
            }
        }

    }
}