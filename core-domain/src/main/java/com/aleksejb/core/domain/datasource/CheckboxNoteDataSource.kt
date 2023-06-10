package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.CheckboxNote
//import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.flow.Flow

interface CheckboxNoteDataSource {
    fun getCheckboxNotesPagingDataAsFlow(): Flow<PagingData<CheckboxNote>>
    suspend fun getCheckboxNoteById(id: Int): CheckboxNote?
    suspend fun insertCheckboxNote(checkboxNote: CheckboxNote)
}