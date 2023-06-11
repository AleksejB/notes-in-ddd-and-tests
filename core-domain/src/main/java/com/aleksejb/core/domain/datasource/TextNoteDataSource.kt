package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow

interface TextNoteDataSource {
    fun getTextNotesPagingDataAsFlow(): Flow<PagingData<TextNote>>
    suspend fun getTextNoteById(id: Int): TextNote?
    suspend fun insertTextNote(textNote: TextNote)
}