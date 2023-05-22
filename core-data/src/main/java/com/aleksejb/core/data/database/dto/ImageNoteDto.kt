package com.aleksejb.core.data.database.dto

import androidx.room.Entity

@Entity(tableName = "image_note")
data class ImageNoteDto(
    val id: Int,
    val title: String
) {
}