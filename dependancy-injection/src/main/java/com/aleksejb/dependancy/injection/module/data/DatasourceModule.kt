package com.aleksejb.dependancy.injection.module.data

import com.aleksejb.core.data.datasource.CheckboxNoteDataSourceImpl
import com.aleksejb.core.data.datasource.ImageNoteDataSourceImpl
import com.aleksejb.core.data.datasource.TextNoteDataSourceImpl
import com.aleksejb.core.domain.datasource.CheckboxNoteDataSource
import com.aleksejb.core.domain.datasource.ImageNoteDataSource
import com.aleksejb.core.domain.datasource.TextNoteDataSource
import org.koin.dsl.module

val datasourceModule = module {
    factory<CheckboxNoteDataSource> { CheckboxNoteDataSourceImpl(get()) }
//    factory<ImageNoteDataSource> { ImageNoteDataSourceImpl(get()) }
    factory<TextNoteDataSource> { TextNoteDataSourceImpl(get()) }
}