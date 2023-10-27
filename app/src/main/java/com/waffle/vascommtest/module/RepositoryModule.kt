package com.waffle.vascommtest.module

import com.waffle.vascommtest.data.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AppRepository(get())
    }
}