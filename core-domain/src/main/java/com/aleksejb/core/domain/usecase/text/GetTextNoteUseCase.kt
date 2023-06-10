package com.aleksejb.core.domain.usecase.text

import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote

class GetTextNoteUseCase(
    private val textNoteDataSource: TextNoteDataSource
) {
    suspend operator fun invoke(id: Int): TextNote? {
        return textNoteDataSource.getTextNoteById(id)
    }
}