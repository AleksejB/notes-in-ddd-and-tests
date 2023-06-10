package com.aleksejb.dependancy.injection

import com.aleksejb.dependancy.injection.module.data.dataModule
import com.aleksejb.dependancy.injection.module.domain.domainModule
import com.aleksejb.dependancy.injection.module.ui.uiModule
import org.koin.dsl.module

val appModule = module {
    includes(uiModule)

    includes(domainModule)

    includes(dataModule)
}