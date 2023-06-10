package com.aleksejb.core.domain.usecase.image

import androidx.paging.PagingData
import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

class GetImageNotesPagingDataUseCase(
    private val imageNoteDataSource: ImageNoteDataSource
) {
    operator fun invoke(): Flow<PagingData<ImageNote>> {
        return imageNoteDataSource.getImageNotesPagingDataAsFlow()
    }
}