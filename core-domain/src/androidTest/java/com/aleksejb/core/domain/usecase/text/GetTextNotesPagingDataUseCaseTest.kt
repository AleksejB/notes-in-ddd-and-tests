package com.aleksejb.core.domain.usecase.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.datasource.TextNoteFakeDataSource
import com.aleksejb.core.domain.model.TextNote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetTextNotesPagingDataUseCaseTest {

    lateinit var getTextNotesPagingDataUseCase: GetTextNotesPagingDataUseCase

    val textNotes = createTextNotes()


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun initGetTextNotesPagingDataUseCase() {
        getTextNotesPagingDataUseCase = GetTextNotesPagingDataUseCase(TextNoteFakeDataSource(textNotes))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPagingData() {
        runTest {
            val actual = getTextNotesPagingDataUseCase.invoke()

            @Composable
            fun TestTextNotePagingItems() {
                val actual = actual.collectAsLazyPagingItems()
                val actualValue = actual.itemSnapshotList.items

                assertEquals(textNotes, actualValue)
            }

            composeTestRule.setContent {
                TestTextNotePagingItems()
            }
        }
    }


    private fun createTextNotes() = listOf(
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
        ),
        TextNote(
            id = 5,
            title = "title5",
            text = "text5"
        ),
        TextNote(
            id = 6,
            title = "title6",
            text = "text6"
        ),
        TextNote(
            id = 7,
            title = "title7",
            text = "text7"
        ),
        TextNote(
            id = 8,
            title = "title8",
            text = "text8"
        ),
        TextNote(
            id = 9,
            title = "title9",
            text = "text9"
        ),
        TextNote(
            id = 10,
            title = "title10",
            text = "text10"
        )
    )
}