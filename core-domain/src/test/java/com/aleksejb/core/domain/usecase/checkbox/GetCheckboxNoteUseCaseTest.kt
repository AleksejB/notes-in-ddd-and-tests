package com.aleksejb.core.domain.usecase.checkbox

import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.datasource.CheckboxNoteFakeDataSource
import com.aleksejb.core.domain.model.CheckboxNote
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetCheckboxNoteUseCaseTest {

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
    fun getCheckboxNoteUseCaseTest_nonExistentNoteId_returnsNull() =
        runTest {
            //Given - the fake data source and
            val noteId = -1
            //When
            val note = checkboxNoteFakeDataSource.getCheckboxNoteById(noteId)
            //Then
            assertEquals(checkboxNotes.find { noteId == it.id }, note)
        }

    @Test
    fun getCheckboxNoteUseCaseTest_validNoteId_returnsNote() =
        runTest {
            //Given - the fake data source and
            val noteId = 1
            //When
            val note = checkboxNoteFakeDataSource.getCheckboxNoteById(noteId)
            //Then
            assertEquals(checkboxNotes.find { noteId == it.id }, note)
        }
}