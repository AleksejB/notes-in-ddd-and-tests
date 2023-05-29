package com.aleksejb.notesinddd.koin

import com.aleksejb.dependancy.injection.module.ui.notesUiModule
import org.koin.dsl.module

val appModule = module {
    includes(
        notesUiModule
    )
}