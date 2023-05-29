package com.aleksejb.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_note")
data class ImageNoteEntity(
    @PrimaryKey val id: Int,
    val title: String,

) {
}