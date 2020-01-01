package com.jeremyg.difftest

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single { ItemRepository() }
    viewModel { ItemViewModel(get()) }
}