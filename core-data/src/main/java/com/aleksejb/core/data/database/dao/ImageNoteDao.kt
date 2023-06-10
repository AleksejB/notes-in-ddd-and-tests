package com.aleksejb.core.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksejb.core.data.database.entity.ImageNoteEntity

@Dao
interface ImageNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageNoteEntity: ImageNoteEntity)

    @Query("SELECT * FROM image_note")
    fun getImageNotePagingSource(): PagingSource<Int, ImageNoteEntity>

    @Query("SELECT * FROM image_note WHERE id = :id")
    suspend fun getImageNoteById(id: Int): ImageNoteEntity?
}