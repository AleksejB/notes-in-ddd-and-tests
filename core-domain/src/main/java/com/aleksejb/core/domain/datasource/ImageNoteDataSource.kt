package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

interface ImageNoteDataSource {

    fun getImageNotesPagingDataAsFlow(): Flow<PagingData<ImageNote>>
    fun getImageNoteById(id: Int): Flow<ImageNote>
    suspend fun insertImageNote(imageNote: ImageNote)
}