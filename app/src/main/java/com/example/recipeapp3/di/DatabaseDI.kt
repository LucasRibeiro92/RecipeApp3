package com.example.recipeapp3.di

import android.content.Context
import androidx.room.Room
import com.example.recipeapp3.db.RecipeDatabase
import com.example.recipeapp3.utils.Constants

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, RecipeDatabase::class.java, Constants.RECIPE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db: RecipeDatabase) = db.recipeDao()