package com.aleksejb.core.domain.usecase.image

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
class GetImageNoteUseCaseTest {

    lateinit var getImageNoteUseCase: GetImageNoteUseCase

    val imageNotes = mutableListOf(
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
    fun initGetImageNoteUseCase() {
        getImageNoteUseCase = GetImageNoteUseCase(ImageNoteFakeDataSource(imageNotes))
    }

    @Test
    fun getImageNoteUseCase_nonExistentNoteId_returnsNull() =
        runTest {
            //Given - the fake data source and
            val noteId = -1
            //When
            val note = getImageNoteUseCase.invoke(noteId)
            //Then
            assertEquals(imageNotes.find { noteId == it.id }, note)
        }

    @Test
    fun getImageNoteUseCase_validNoteId_returnsNote() =
        runTest {
            //Given - the fake data source and
            val noteId = 1
            //When
            val note = getImageNoteUseCase.invoke(noteId)
            //Then
            assertEquals(imageNotes.find { noteId == it.id }, note)
        }
}