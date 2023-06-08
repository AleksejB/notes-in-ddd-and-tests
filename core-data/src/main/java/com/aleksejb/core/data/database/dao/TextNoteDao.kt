package com.aleksejb.core.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksejb.core.data.database.entity.TextNoteEntity

@Dao
interface TextNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(textNoteEntity: TextNoteEntity): Long

    @Query("SELECT * FROM text_note WHERE id = :id")
    suspend fun getNoteById(id: Int): TextNoteEntity?

    @Query("SELECT * FROM text_note")
    fun getTextNotesPagingSource(): PagingSource<Int, TextNoteEntity>
}