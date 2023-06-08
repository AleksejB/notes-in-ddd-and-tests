package com.aleksejb.core.domain.usecase

import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow

class GetTextNoteFlowUseCase(
    private val textNoteDataSource: TextNoteDataSource
) {
    suspend operator fun invoke(id: Int): TextNote? {
        return textNoteDataSource.getTextNoteById(id)
    }
}