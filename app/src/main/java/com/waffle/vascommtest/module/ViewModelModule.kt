package com.waffle.vascommtest.module

import com.waffle.vascommtest.ui.login.LoginViewModel
import com.waffle.vascommtest.ui.register.RegisterViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { LoginViewModel(get()) }
    single { RegisterViewModel(get()) }
}