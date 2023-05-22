package com.aleksejb.core.data.database.dto

import androidx.room.Entity

@Entity(tableName = "text_note")
data class TextNoteDto(
    val id: String,
    val title: String,
    val text: String
)