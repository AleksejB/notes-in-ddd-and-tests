package com.aleksejb.dependancy.injection.module.data

import org.koin.dsl.module

val dataModule = module {
    includes(
        databaseModule,
        datasourceModule
    )
}