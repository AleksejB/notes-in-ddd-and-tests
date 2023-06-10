package com.aleksejb.core.domain.usecase.text

import android.util.Log
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote

class SaveTextNoteUseCase(
    private val textNoteDataSource: TextNoteDataSource
) {
    suspend operator fun invoke(textNote: TextNote) {
        textNoteDataSource.insertTextNote(textNote)
    }
}