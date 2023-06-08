package com.aleksejb.core.domain.usecase

import android.util.Log
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import com.aleksejb.core.domain.model.TextNote

class SaveTextNoteUseCase(
    private val textNoteDataSource: TextNoteDataSource
) {
    suspend operator fun invoke(textNote: TextNote) {
        val rowsAffected = textNoteDataSource.insertTextNote(textNote)
        Log.d("TAAAG", "rowsAffected: $rowsAffected")
    }
}