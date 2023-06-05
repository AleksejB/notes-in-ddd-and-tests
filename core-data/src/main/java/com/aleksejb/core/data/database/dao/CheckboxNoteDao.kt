package com.aleksejb.core.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksejb.core.data.database.entity.CheckboxNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckboxNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkboxNoteEntity: CheckboxNoteEntity)

    @Query("SELECT * FROM checkbox_note WHERE id = :id")
    fun getNoteByIdAsFlow(id: Int): Flow<CheckboxNoteEntity>

    @Query("SELECT * FROM checkbox_note")
    fun getTextNotesPagingSource(): PagingSource<Int, CheckboxNoteEntity>
}