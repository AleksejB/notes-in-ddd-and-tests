package com.aleksejb.ui.image.note

import android.graphics.Bitmap

sealed interface ImageNoteEvent {
    data class OnTitleChanged(val input: String): ImageNoteEvent
    data class OnNewImageSelected(val bitmap: Bitmap): ImageNoteEvent
}