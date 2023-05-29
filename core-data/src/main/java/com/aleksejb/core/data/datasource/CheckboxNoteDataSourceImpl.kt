package com.aleksejb.core.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aleksejb.core.data.database.dao.CheckboxNoteDao
import com.aleksejb.core.data.database.entity.toCheckboxNote
import com.aleksejb.core.data.database.entity.toCheckboxNoteEntity
import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckboxNoteDataSourceImpl(
    private val checkboxNoteDao: CheckboxNoteDao
): CheckboxNoteDataSource {

    override fun getCheckboxNotesPagingDataAsFlow(): Flow<PagingData<CheckboxNote>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { checkboxNoteDao.getTextNotesPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { checkboxNoteEntity ->
                checkboxNoteEntity.toCheckboxNote()
            }
        }
    }

    override fun getCheckboxNoteById(id: Int): Flow<CheckboxNote> {
        return checkboxNoteDao.getNoteByIdAsFlow(id).map { checkboxNoteEntity ->
            checkboxNoteEntity.toCheckboxNote()
        }
    }

    override suspend fun insertCheckboxNote(checkboxNote: CheckboxNote) {
        checkboxNoteDao.insert(checkboxNote.toCheckboxNoteEntity())
    }
}