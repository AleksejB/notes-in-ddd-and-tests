package com.aleksejb.core.domain.usecase.checkbox

import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.flow.Flow

class GetCheckboxNoteUseCase(
    private val checkboxNoteDataSource: CheckboxNoteDataSource
) {
    suspend operator fun invoke(id: Int): CheckboxNote? {
        return checkboxNoteDataSource.getCheckboxNoteById(id)
    }
}