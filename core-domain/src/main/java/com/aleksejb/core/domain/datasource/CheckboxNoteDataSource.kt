package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.flow.Flow

interface CheckboxNoteDataSource {
    fun getCheckboxNotesPagingDataAsFlow(): Flow<PagingData<CheckboxNote>>
    fun getCheckboxNoteById(id: Int): Flow<CheckboxNote>
    suspend fun insertCheckboxNote(checkboxNote: CheckboxNote)
}