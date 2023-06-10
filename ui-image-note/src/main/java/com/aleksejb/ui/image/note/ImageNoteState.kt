package com.aleksejb.ui.image.note

import android.graphics.Bitmap

data class ImageNoteState(
    val title: String = "Title",
    val images: List<Bitmap> = emptyList()
)