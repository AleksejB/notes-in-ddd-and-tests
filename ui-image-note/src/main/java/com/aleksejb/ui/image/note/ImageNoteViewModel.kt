package com.aleksejb.ui.image.note

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.usecase.image.GetImageNoteUseCase
import com.aleksejb.core.domain.usecase.image.SaveImageNoteUseCase
import com.aleksejb.core.domain.util.noteDoesNotExists
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImageNoteViewModel(
    private val noteId: Int?,
    private val getImageNoteUseCase: GetImageNoteUseCase,
    private val saveImageNoteUseCase: SaveImageNoteUseCase,
    private val scope: CoroutineScope
): MVIViewModel<ImageNoteState, ImageNoteEvent, ImageNoteEffect>() {

    override val _state = MutableStateFlow(ImageNoteState())

    init { getNoteIfExists() }

    fun onDispose() = scope.launch { saveNote() }

    override fun handleEvent(event: ImageNoteEvent) {
        when (event) {
            is ImageNoteEvent.OnTitleChanged -> updateTitle(event.input)
            is ImageNoteEvent.OnNewImageSelected -> updateImages(event.bitmap)
        }
    }

    private fun updateImages(bitmap: Bitmap) {
        val new = getNewImages(bitmap)
        updateState { copy(images = new) }
    }

    private fun getNewImages(bitmap: Bitmap) = state.value.images.plus(bitmap)

    private fun updateTitle(title: String) {
        updateState { copy(title = title) }
    }

    private suspend fun saveNote() {
        Log.d("TAAAG", "saving note")
        saveImageNoteUseCase.invoke(
            ImageNote(
                id = noteId,
                title = state.value.title,
                images = state.value.images
            )
        )
    }

    private fun getNoteIfExists() {
        viewModelScope.launch {
            if (noteId.noteDoesNotExists()) return@launch

            getImageNoteUseCase.invoke(noteId!!)?.let { note ->
                updateState { copy(title = note.title, images = note.images) }
            }
        }
    }

}