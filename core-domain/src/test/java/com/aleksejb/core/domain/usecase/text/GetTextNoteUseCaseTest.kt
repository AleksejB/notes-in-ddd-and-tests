package com.aleksejb.core.domain.usecase.text

import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.datasource.TextNoteFakeDataSource
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GetTextNoteUseCaseTest {

    lateinit var getTextNoteUseCase: GetTextNoteUseCase

    val textNotes = listOf(
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
    fun initGetTextNoteUseCase() {
        getTextNoteUseCase = GetTextNoteUseCase(TextNoteFakeDataSource(textNotes))
    }

    @Test
    fun getTextNoteUseCaseTest_nonExistentNoteId_returnsNull() =
        runTest {
            //Given - the fake data source and
            val noteId = -1
            //When
            val note = getTextNoteUseCase.invoke(noteId)
            //Then
            assertEquals(null, note)
        }

    @Test
    fun getTextNoteUseCaseTest_validNoteId_returnsNote() =
        runTest {
            //Given - the fake data source and
            val noteId = 1
            //When
            val note = getTextNoteUseCase.invoke(noteId)
            //Then
            assertEquals(textNotes.find { it.id == noteId }, note)
        }
}