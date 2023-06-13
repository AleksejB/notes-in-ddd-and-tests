package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TextNoteFakeDataSource(
    val textNotes: List<TextNote>
): TextNoteDataSource {

    override fun getTextNotesPagingDataAsFlow(): Flow<PagingData<TextNote>> {
        return flowOf(PagingData.from(textNotes))
    }

    override suspend fun getTextNoteById(id: Int): TextNote? {
        TODO("Not yet implemented")
    }

    override suspend fun insertTextNote(textNote: TextNote) {
        TODO("Not yet implemented")
    }

}