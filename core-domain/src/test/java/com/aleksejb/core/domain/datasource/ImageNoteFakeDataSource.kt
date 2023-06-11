package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

class ImageNoteFakeDataSource(
    var imageNotes: List<ImageNote>
): ImageNoteDataSource {

    override fun getImageNotesPagingDataAsFlow(): Flow<PagingData<ImageNote>> {
        TODO("Not yet implemented")
    }

    override suspend fun getImageNoteById(id: Int): ImageNote? {
        return imageNotes.find { it.id == id }

    }

    override suspend fun insertImageNote(imageNote: ImageNote) {
        imageNotes = if (imageNote.id == null) {
            //insert new note
            val note = generateNoteWithNewId(imageNote)
            val newImageNotes = imageNotes.plus(note)
            newImageNotes
        } else if (imageNotes.find { it.id == imageNote.id } == null) {
            //insert new note with the id
            val newImageNotes = imageNotes.plus(imageNote)
            newImageNotes
        } else {
            //replace note
            val newImageNotes = imageNotes.map { note ->
                if (note.id == imageNote.id) {
                    imageNote
                } else note
            }
            newImageNotes
        }
    }

    private fun generateNoteWithNewId(imageNote: ImageNote): ImageNote {
        return ImageNote(
            id = imageNotes.size + 1,
            title = imageNote.title,
            images = emptyList()
        )
    }
}