package com.aleksejb.core.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksejb.core.data.database.entity.TextNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TextNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(textNoteEntity: TextNoteEntity)

    @Query("SELECT * FROM text_note WHERE id = :id")
    fun getNoteByIdAsFlow(id: Int): Flow<TextNoteEntity?>

    @Query("SELECT * FROM text_note")
    fun getTextNotesPagingSource(): PagingSource<Int, TextNoteEntity>
}