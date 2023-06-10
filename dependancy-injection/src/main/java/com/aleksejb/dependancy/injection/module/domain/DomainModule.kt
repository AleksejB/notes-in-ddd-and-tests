package com.aleksejb.dependancy.injection.module.domain

import com.aleksejb.core.domain.usecase.checkbox.GetCheckboxNoteUseCase
import com.aleksejb.core.domain.usecase.checkbox.GetCheckboxNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.checkbox.SaveCheckboxNoteUseCase
import com.aleksejb.core.domain.usecase.image.GetImageNoteUseCase
import com.aleksejb.core.domain.usecase.image.GetImageNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.image.SaveImageNoteUseCase
import com.aleksejb.core.domain.usecase.text.GetTextNoteUseCase
import com.aleksejb.core.domain.usecase.text.GetTextNotesPagingDataUseCase
import com.aleksejb.core.domain.usecase.text.SaveTextNoteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetCheckboxNoteUseCase(get()) }
    factory { GetCheckboxNotesPagingDataUseCase(get()) }
    factory { SaveCheckboxNoteUseCase(get()) }

    factory { GetImageNoteUseCase(get()) }
    factory { GetImageNotesPagingDataUseCase(get()) }
    factory { SaveImageNoteUseCase(get()) }

    factory { GetTextNoteUseCase(get()) }
    factory { GetTextNotesPagingDataUseCase(get()) }
    factory { SaveTextNoteUseCase(get()) }
}