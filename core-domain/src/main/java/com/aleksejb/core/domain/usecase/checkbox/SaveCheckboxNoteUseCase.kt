package com.aleksejb.core.domain.usecase.checkbox

import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.model.CheckboxNote

class SaveCheckboxNoteUseCase(
    private val checkboxNoteDataSource: CheckboxNoteDataSource
) {
    suspend operator fun invoke(checkboxNote: CheckboxNote) {
        checkboxNoteDataSource.insertCheckboxNote(checkboxNote)
    }
}