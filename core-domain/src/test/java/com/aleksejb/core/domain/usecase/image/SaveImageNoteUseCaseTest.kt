package com.aleksejb.core.domain.usecase.image

import android.util.Log
import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.datasource.ImageNoteFakeDataSource
import com.aleksejb.core.domain.model.ImageNote
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SaveImageNoteUseCaseTest {

    lateinit var imageNoteFakeDataSource: ImageNoteDataSource

    var imageNotes = listOf(
        ImageNote(
            id = 1,
            title = "title1",
            images = emptyList()
        ),
        ImageNote(
            id = 2,
            title = "title2",
            images = emptyList()
        ),
        ImageNote(
            id = 3,
            title = "title3",
            images = emptyList()
        ),
        ImageNote(
            id = 4,
            title = "title4",
            images = emptyList()
        )
    )

    @Before
    fun initImageNoteFakeDataSource() {
        imageNoteFakeDataSource = ImageNoteFakeDataSource(imageNotes)
    }

    @Test
    fun insertImageNote_givenExistingId_replacesExistingNot() = runTest {
        //Given - the fake data source and a
        val newNote = ImageNote(
            id = 1,
            title = "newTitle1",
            images = emptyList()
        )
        //When
        imageNoteFakeDataSource.insertImageNote(newNote)
        //Then
        val note = imageNoteFakeDataSource.getImageNoteById(1)
        assertEquals(newNote, note)
    }

    @Test
    fun insertImageNote_givenNewId_savesNote() = runTest {
        //Given - the fake data source and a
        val newNote = ImageNote(
            id = 5,
            title = "title5",
            images = emptyList()
        )
        //When
        imageNoteFakeDataSource.insertImageNote(newNote)
        //Then
        val note = imageNoteFakeDataSource.getImageNoteById(5)
        assertEquals(newNote, note)
    }

    @Test
    fun insertImageNote_givenNullId_savesNewNote() = runTest {
        //Given - the fake data source and a
        val newNote = ImageNote(
            id = null,
            title = "title5",
            images = emptyList()
        )
        //When
        imageNoteFakeDataSource.insertImageNote(newNote)
        //Then
        val note = imageNoteFakeDataSource.getImageNoteById(imageNotes.size + 1)
        assertEquals(
            ImageNote(
                id = imageNotes.size + 1,
                title = newNote.title,
                images = newNote.images
            ),
            note
        )
    }
}