package com.example.recipeapp3

import android.app.Application
import com.example.recipeapp3.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            /** Context **/
            /** Context **/
            androidContext(this@MyApp)
            /** Room Database **/
            /** Room Database **/
            modules(databaseModule)
        }
    }
}