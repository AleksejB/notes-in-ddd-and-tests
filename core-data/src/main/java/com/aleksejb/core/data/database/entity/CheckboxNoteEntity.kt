package com.aleksejb.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aleksejb.core.domain.model.CheckboxItem
import com.aleksejb.core.domain.model.CheckboxNote

@Entity(tableName = "checkbox_note")
data class CheckboxNoteEntity(
    @PrimaryKey val id: Int,
    val title: String,
//    @field:TypeConverters(CheckboxItem::class)
    val items: List<CheckboxItem>
)

fun CheckboxNoteEntity.toCheckboxNote() = CheckboxNote(
    id = id,
    title = title,
    items = items
)

fun CheckboxNote.toCheckboxNoteEntity() = CheckboxNoteEntity(
    id = id,
    title = title,
    items = items
)