package com.example.pizzaoven.di

import com.example.pizzaoven.ui.screen.MainActivityViewModel

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { MainActivityViewModel() }
}