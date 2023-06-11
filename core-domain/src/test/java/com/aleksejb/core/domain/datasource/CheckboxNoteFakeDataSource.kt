package com.aleksejb.core.domain.datasource

import androidx.paging.PagingData
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.flow.Flow

class CheckboxNoteFakeDataSource(
    var checkboxNotes: List<CheckboxNote>
): CheckboxNoteDataSource {

    override fun getCheckboxNotesPagingDataAsFlow(): Flow<PagingData<CheckboxNote>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCheckboxNoteById(id: Int): CheckboxNote? {
        return checkboxNotes.find { it.id == id }
    }

    override suspend fun insertCheckboxNote(checkboxNote: CheckboxNote) {
        checkboxNotes = if (checkboxNote.id == null) {
            //insert new note
            val note = generateNoteWithNewId(checkboxNote)
            val newCheckboxNotes = checkboxNotes.plus(note)
            newCheckboxNotes
        } else if (checkboxNotes.find { it.id == checkboxNote.id } == null) {
            //insert new note with the id
            val newCheckboxNotes = checkboxNotes.plus(checkboxNote)
            newCheckboxNotes
        } else {
            //replace note
            val newCheckboxNotes = checkboxNotes.map { note ->
                if (note.id == checkboxNote.id) {
                    checkboxNote
                } else note
            }
            newCheckboxNotes
        }
    }

    private fun generateNoteWithNewId(checkboxNote: CheckboxNote): CheckboxNote {
        return CheckboxNote(
            id = checkboxNotes.size + 1,
            title = checkboxNote.title,
            items = checkboxNote.items
        )
    }
}