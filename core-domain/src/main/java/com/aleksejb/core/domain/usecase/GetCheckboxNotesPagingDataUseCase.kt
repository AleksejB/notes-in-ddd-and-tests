package com.aleksejb.core.domain.usecase

import androidx.paging.PagingData
import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.flow.Flow

class GetCheckboxNotesPagingDataUseCase(
    private val checkboxNoteDataSource: CheckboxNoteDataSource
) {
    operator fun invoke(): Flow<PagingData<CheckboxNote>> {
        return checkboxNoteDataSource.getCheckboxNotesPagingDataAsFlow()
    }
}