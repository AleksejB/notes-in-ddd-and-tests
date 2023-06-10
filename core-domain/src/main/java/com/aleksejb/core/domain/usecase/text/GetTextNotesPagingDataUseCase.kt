package com.aleksejb.core.domain.usecase.text

import androidx.paging.PagingData
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow

class GetTextNotesPagingDataUseCase(
    private val textNoteDataSource: TextNoteDataSource
) {

    operator fun invoke(): Flow<PagingData<TextNote>> {
        return textNoteDataSource.getTextNotesPagingDataAsFlow()
    }
}