package com.aleksejb.ui.text.note

import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.model.TextNote
import com.aleksejb.core.domain.usecase.GetTextNoteFlowUseCase
import com.aleksejb.core.domain.usecase.SaveTextNoteUseCase
import com.aleksejb.core.domain.util.Constants.NON_EXISTENT_NOTE_ID
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TextNoteViewModel(
    private val noteId: Int,
    private val getTextNoteFlowUseCase: GetTextNoteFlowUseCase,
    private val saveTextNoteUseCase: SaveTextNoteUseCase
): MVIViewModel<TextNoteState, TextNoteEvent, TextNoteEffect>() {

    override val _state = MutableStateFlow(TextNoteState())

    init { observeNoteIfExists() }

    override fun handleEvent(event: TextNoteEvent) {
        when (event) {
            is TextNoteEvent.OnTitleChanged -> {
                viewModelScope.launch {
                    if (noteExists()) saveNote() else updateTitle(event.input)
                }
            }
            is TextNoteEvent.OnTextChanged -> {
                viewModelScope.launch {
                    if (noteExists()) saveNote() else updateText(event.input)
                }
            }
        }
    }

    fun onDispose() {
        viewModelScope.launch {
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

    private suspend fun saveNote() {
        saveTextNoteUseCase.invoke(
            TextNote(
                id = noteId,
                title = state.value.title,
                text = state.value.text
            )
        )
    }

    private fun noteExists() = noteId != NON_EXISTENT_NOTE_ID

    private fun observeNoteIfExists() {
        viewModelScope.launch {
            if (noteId == NON_EXISTENT_NOTE_ID) return@launch

            getTextNoteFlowUseCase.invoke(noteId).collect { note ->
                note?.let { updateState { copy(title = it.title, text = it.text) } }
            }
        }
    }
}