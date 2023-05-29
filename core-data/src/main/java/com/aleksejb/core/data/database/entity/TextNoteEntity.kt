package com.aleksejb.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleksejb.core.domain.model.TextNote

@Entity(tableName = "text_note")
data class TextNoteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String
)

fun TextNoteEntity.toTextNote() = TextNote(
    id = id,
    title = title,
    text = text
)

fun TextNote.toTextNoteEntity() = TextNoteEntity(
    id = id,
    title = title,
    text = text
)