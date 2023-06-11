package com.aleksejb.core.domain.usecase.text

import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.datasource.ImageNoteFakeDataSource
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.datasource.TextNoteFakeDataSource
import com.aleksejb.core.domain.model.ImageNote
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SaveTextNoteUseCaseTest {

    lateinit var textNoteFakeDataSource: TextNoteDataSource

    var textNotes = listOf(
        TextNote(
            id = 1,
            title = "title1",
            text = "text1"
        ),
        TextNote(
            id = 2,
            title = "title2",
            text = "text2"
        ),
        TextNote(
            id = 3,
            title = "title3",
            text = "text3"
        ),
        TextNote(
            id = 4,
            title = "title4",
            text = "text4"
        )
    )

    @Before
    fun initImageNoteFakeDataSource() {
        textNoteFakeDataSource = TextNoteFakeDataSource(textNotes)
    }

    @Test
    fun insertTextNote_givenExistingId_replacesExistingNote() = runTest {
        //Given - the fake data source and a
        val newNote = TextNote(
            id = 1,
            title = "newTitle1",
            text = "newText1"
        )
        //When
        textNoteFakeDataSource.insertTextNote(newNote)
        //Then
        val note = textNoteFakeDataSource.getTextNoteById(1)
        assertEquals(newNote, note)
    }

    @Test
    fun insertTextNote_givenNewId_savesNote() = runTest {
        //Given - the fake data source and a
        val newNote = TextNote(
            id = 5,
            title = "title5",
            text = "text5"
        )
        //When
        textNoteFakeDataSource.insertTextNote(newNote)
        //Then
        val note = textNoteFakeDataSource.getTextNoteById(5)
        assertEquals(newNote, note)
    }

    @Test
    fun insertTextNote_givenNullId_savesNewNote() = runTest {
        //Given - the fake data source and a
        val newNote = TextNote(
            id = null,
            title = "title5",
            text = "text5"
        )
        //When
        textNoteFakeDataSource.insertTextNote(newNote)
        //Then
        val note = textNoteFakeDataSource.getTextNoteById(textNotes.size + 1)
        assertEquals(
            TextNote(
                id = 5,
                title = "title5",
                text = "text5"
            ),
            note
        )
    }
}