package com.aleksejb.core.domain.model

import android.graphics.Bitmap

data class ImageNote(
    val id: Int?,
    val title: String,
    val images: List<Bitmap>
): Note