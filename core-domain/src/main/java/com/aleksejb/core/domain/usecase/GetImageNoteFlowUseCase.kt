package com.aleksejb.core.domain.usecase

import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

class GetImageNoteFlowUseCase(
    private val imageNoteDataSource: ImageNoteDataSource
) {
    operator fun invoke(id: Int): Flow<ImageNote> {
        return imageNoteDataSource.getImageNoteById(id)
    }
}