package com.aleksejb.core.data.database.dto

import androidx.room.Entity

@Entity(tableName = "checkbox_note")
data class CheckboxNoteDto(
    val id: Int,
    val title: String,
//    val items
)