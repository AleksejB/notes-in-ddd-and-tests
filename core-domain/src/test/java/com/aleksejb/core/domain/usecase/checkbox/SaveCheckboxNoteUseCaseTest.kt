package com.aleksejb.core.domain.usecase.checkbox

import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.datasource.CheckboxNoteFakeDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SaveCheckboxNoteUseCaseTest {

    lateinit var checkboxNoteFakeDataSource: CheckboxNoteDataSource

    val checkboxNotes = listOf(
        CheckboxNote(
            id = 1,
            title = "title1",
            items = emptyList()
        ),
        CheckboxNote(
            id = 2,
            title = "title2",
            items = emptyList()
        ),
        CheckboxNote(
            id = 3,
            title = "title3",
            items = emptyList()
        ),
        CheckboxNote(
            id = 4,
            title = "title4",
            items = emptyList()
        )
    )

    @Before
    fun initCheckboxNoteFakeDataSource() {
        checkboxNoteFakeDataSource = CheckboxNoteFakeDataSource(checkboxNotes)
    }

    @Test
    fun insertTextNote_givenExistingId_replacesExistingNot() = runTest {
        //Given - the fake data source and a
        val newNote = CheckboxNote(
            id = 1,
            title = "newTitle1",
            items = emptyList()
        )
        //When
        checkboxNoteFakeDataSource.insertCheckboxNote(newNote)
        //Then
        val note = checkboxNoteFakeDataSource.getCheckboxNoteById(1)
        assertEquals(
            newNote,
            note
        )
    }

    @Test
    fun insertTextNote_givenNewId_savesNote() = runTest {
        //Given - the fake data source and a
        val newNote = CheckboxNote(
            id = 5,
            title = "title5",
            items = emptyList()
        )
        //When
        checkboxNoteFakeDataSource.insertCheckboxNote(newNote)
        //Then
        val note = checkboxNoteFakeDataSource.getCheckboxNoteById(5)
        assertEquals(newNote, note)
    }

    @Test
    fun insertTextNote_givenNullId_savesNewNote() = runTest {
        //Given - the fake data source and a
        val newNote = CheckboxNote(
            id = null,
            title = "title5",
            items = emptyList()
        )
        //When
        checkboxNoteFakeDataSource.insertCheckboxNote(newNote)
        //Then
        val note = checkboxNoteFakeDataSource.getCheckboxNoteById(checkboxNotes.size + 1)
        assertEquals(
            CheckboxNote(
                id = checkboxNotes.size + 1,
                title = newNote.title,
                items = newNote.items
            ),
            note
        )
    }
}