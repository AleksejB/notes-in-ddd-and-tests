package com.aleksejb.dependancy.injection.module.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val coroutineModule = module {
    factory<CoroutineScope> { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
}