package com.aleksejb.ui.text.note

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.model.TextNote
import com.aleksejb.core.domain.usecase.text.GetTextNoteUseCase
import com.aleksejb.core.domain.usecase.text.SaveTextNoteUseCase
import com.aleksejb.core.domain.util.noteDoesNotExists
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class TextNoteViewModel(
    private val noteId: Int?,
    private val getTextNoteUseCase: GetTextNoteUseCase,
    private val saveTextNoteUseCase: SaveTextNoteUseCase,
    private val scope: CoroutineScope
): MVIViewModel<TextNoteState, TextNoteEvent, TextNoteEffect>() {

    override val _state = MutableStateFlow(TextNoteState())

    init { getNoteIfExists() }

    override fun handleEvent(event: TextNoteEvent) {
        when (event) {
            is TextNoteEvent.OnTitleChanged -> updateTitle(event.input)
            is TextNoteEvent.OnTextChanged -> updateText(event.input)
        }
    }

    fun onDispose() = scope.launch { saveNote() }

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

    private fun getNoteIfExists() {
        viewModelScope.launch {
            if (noteId.noteDoesNotExists()) return@launch

            getTextNoteUseCase.invoke(noteId!!)?.let {
                updateState { copy(title = it.title, text = it.text) }
            }
        }

    }
}