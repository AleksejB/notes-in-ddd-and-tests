package com.aleksejb.notesinddd.koin

import com.aleksejb.ui.notes.koin.notesUiModule
import org.koin.dsl.module

val appModule = module {
    includes(
        notesUiModule
    )
}