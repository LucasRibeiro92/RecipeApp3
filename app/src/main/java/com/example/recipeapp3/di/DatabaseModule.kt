package com.example.recipeapp3.di

import com.example.recipeapp3.repository.DatabaseRepository
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {

    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
    factory { DatabaseRepository(get()) }
    viewModel() { DatabaseViewModel(get()) }

}