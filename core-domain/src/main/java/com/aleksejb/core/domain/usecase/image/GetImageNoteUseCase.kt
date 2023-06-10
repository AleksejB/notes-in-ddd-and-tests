package com.aleksejb.core.domain.usecase.image

import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.model.ImageNote

class GetImageNoteUseCase(
    private val imageNoteDataSource: ImageNoteDataSource
) {
    suspend operator fun invoke(id: Int): ImageNote? {
        return imageNoteDataSource.getImageNoteById(id)
    }
}