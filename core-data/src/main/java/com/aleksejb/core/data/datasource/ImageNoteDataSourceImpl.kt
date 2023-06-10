package com.aleksejb.core.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aleksejb.core.data.database.dao.ImageNoteDao
import com.aleksejb.core.data.database.entity.toImageNote
import com.aleksejb.core.data.database.entity.toImageNoteEntity
import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImageNoteDataSourceImpl(
    private val imageNoteDao: ImageNoteDao
): ImageNoteDataSource {

    override fun getImageNotesPagingDataAsFlow(): Flow<PagingData<ImageNote>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { imageNoteDao.getImageNotePagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { imageNoteEntity ->
                imageNoteEntity.toImageNote()
            }
        }
    }

    override suspend fun getImageNoteById(id: Int): ImageNote? {
        return imageNoteDao.getImageNoteById(id)?.toImageNote()
    }

    override suspend fun insertImageNote(imageNote: ImageNote) {
        imageNoteDao.insert(imageNote.toImageNoteEntity())
    }
}