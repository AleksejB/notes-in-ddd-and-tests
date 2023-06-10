package com.aleksejb.core.domain.usecase.image

import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.model.ImageNote

class SaveImageNoteUseCase(
    private val imageNoteDataSource: ImageNoteDataSource
) {
    suspend operator fun invoke(imageNote: ImageNote) {
        imageNoteDataSource.insertImageNote(imageNote)
    }
}