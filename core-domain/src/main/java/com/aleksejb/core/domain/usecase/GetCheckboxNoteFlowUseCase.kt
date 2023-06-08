package com.aleksejb.core.domain.usecase

import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.flow.Flow

class GetCheckboxNoteFlowUseCase(
    private val checkboxNoteDataSource: CheckboxNoteDataSource
) {
    operator fun invoke(id: Int): Flow<CheckboxNote?> {
        return checkboxNoteDataSource.getCheckboxNoteById(id)
    }
}