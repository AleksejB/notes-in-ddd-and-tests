package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

interface ImageNoteDataSource {

    fun getImageNotesPagingDataAsFlow(): Flow<PagingData<ImageNote>>
    suspend fun getImageNoteById(id: Int): ImageNote?
    suspend fun insertImageNote(imageNote: ImageNote)
}