package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.flow.Flow

class TextNoteFakeDataSource(var textNotes: List<TextNote>): TextNoteDataSource {

    override fun getTextNotesPagingDataAsFlow(): Flow<PagingData<TextNote>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTextNoteById(id: Int): TextNote? {
        return textNotes.find { it.id == id }
    }

    override suspend fun insertTextNote(textNote: TextNote) {
        textNotes = if (textNote.id == null) {
            //insert new note
            val note = generateNoteWithNewId(textNote)
            val newTextNotes = textNotes.plus(note)
            newTextNotes
        } else if (textNotes.find { it.id == textNote.id } == null) {
            //insert new note with the id
            val newTextNotes = textNotes.plus(textNote)
            newTextNotes
        } else {
            //replace note
            val newTextNotes = textNotes.map { note ->
                if (note.id == textNote.id) {
                    textNote
                } else note
            }
            newTextNotes
        }
    }

    private fun generateNoteWithNewId(textNote: TextNote): TextNote {
        return TextNote(
            id = textNotes.size + 1,
            title = textNote.title,
            text = textNote.text
        )
    }
}