package com.aleksejb.core.domain.usecase.text

//import androidx.paging.PagingData
//import com.aleksejb.core.domain.datasource.TextNoteDataSource
//import com.aleksejb.core.domain.datasource.TextNoteFakeDataSource
//import com.aleksejb.core.domain.model.TextNote
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//
//@RunWith(JUnit4::class)
//class GetTextNotesPagingDataUseCaseTest {

//    lateinit var getTextNotesPagingDataUseCase: GetTextNotesPagingDataUseCase
//
//    val textNotes = createTextNotes()

//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    val testCoroutineRule = CoroutineTestRule()

//    @Before
//    fun initGetTextNotesPagingDataUseCase() {
//        getTextNotesPagingDataUseCase = GetTextNotesPagingDataUseCase(TextNoteFakeDataSource(textNotes))
//    }

//    @Test
//    fun getPagingData() {
//        runTest {
//            val expected = textNotes
//
//            val actual = getTextNotesPagingDataUseCase.invoke()
//
//        }
//        //Given
//        val expected = flowOf(PagingData.from(textNotes))
//        //When
//        val actual = getTextNotesPagingDataUseCase.invoke()
//        //Then
//        assertEquals(expected, actual)
//    }

//    fun launchTest(block: suspend TestCoroutineScope.() -> Unit) =
//        testCoroutineRule.testCoroutineScope.launch(testCoroutineRule.testCoroutineDispatcher) { block }

//    private fun createTextNotes() = listOf(
//        TextNote(
//            id = 1,
//            title = "title1",
//            text = "text1"
//        ),
//        TextNote(
//            id = 2,
//            title = "title2",
//            text = "text2"
//        ),
//        TextNote(
//            id = 3,
//            title = "title3",
//            text = "text3"
//        ),
//        TextNote(
//            id = 4,
//            title = "title4",
//            text = "text4"
//        ),
//        TextNote(
//            id = 5,
//            title = "title5",
//            text = "text5"
//        ),
//        TextNote(
//            id = 6,
//            title = "title6",
//            text = "text6"
//        ),
//        TextNote(
//            id = 7,
//            title = "title7",
//            text = "text7"
//        ),
//        TextNote(
//            id = 8,
//            title = "title8",
//            text = "text8"
//        ),
//        TextNote(
//            id = 9,
//            title = "title9",
//            text = "text9"
//        ),
//        TextNote(
//            id = 10,
//            title = "title10",
//            text = "text10"
//        )
//    )
//}