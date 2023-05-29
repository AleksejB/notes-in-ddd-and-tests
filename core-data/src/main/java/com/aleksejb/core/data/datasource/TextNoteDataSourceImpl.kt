package com.aleksejb.core.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aleksejb.core.data.database.dao.TextNoteDao
import com.aleksejb.core.data.database.entity.toTextNote
import com.aleksejb.core.data.database.entity.toTextNoteEntity
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote
import com.aleksejb.core.domain.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TextNoteDataSourceImpl(
    private val textNoteDao: TextNoteDao
): TextNoteDataSource {
    override fun getTextNotesPagingDataAsFlow(): Flow<PagingData<TextNote>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { textNoteDao.getTextNotesPagingSource() }
        ).flow.map {  pagingData ->
            pagingData.map { textNoteEntity ->
                textNoteEntity.toTextNote()
            }
        }
    }

    override fun getTextNoteById(id: Int): Flow<TextNote> {
        return textNoteDao.getNoteByIdAsFlow(id).map { textNoteEntity -> textNoteEntity.toTextNote() }
    }

    override suspend fun insertTextNote(textNote: TextNote) {
        textNoteDao.insert(textNote.toTextNoteEntity())
    }
}
