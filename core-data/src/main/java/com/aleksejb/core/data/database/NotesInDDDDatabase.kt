package com.aleksejb.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleksejb.core.data.database.dao.CheckboxNoteDao
import com.aleksejb.core.data.database.dao.ImageNoteDao
import com.aleksejb.core.data.database.dao.TextNoteDao
import com.aleksejb.core.data.database.entity.CheckboxNoteEntity
import com.aleksejb.core.data.database.entity.ImageNoteEntity
import com.aleksejb.core.data.database.entity.TextNoteEntity
import com.aleksejb.core.data.database.typeconverter.impl.CheckboxItemTypeConverter

@TypeConverters(CheckboxItemTypeConverter::class)
@Database(
    entities = [
        CheckboxNoteEntity::class,
        TextNoteEntity::class,
        ImageNoteEntity::class
    ],
    version = 1
)
abstract class NotesInDDDDatabase(): RoomDatabase() {
    abstract fun getCheckboxNoteDao(): CheckboxNoteDao
    abstract fun getImageNoteDao(): ImageNoteDao
    abstract fun getTextNoteDao(): TextNoteDao
}