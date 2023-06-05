package com.aleksejb.dependancy.injection.module.domain

import com.aleksejb.core.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetCheckboxNoteFlowUseCase(get()) }
    factory { GetCheckboxNotesPagingDataUseCase(get()) }
    factory { GetImageNoteFlowUseCase(get()) }
    factory { GetImageNotesPagerUseCase(get()) }
    factory { GetTextNoteFlowUseCase(get()) }
    factory { GetTextNotesPagingDataUseCase(get()) }
    factory { SaveCheckboxNoteUseCase(get()) }
    factory { SaveImageNoteUseCase(get()) }
    factory { SaveTextNoteUseCase(get()) }
}