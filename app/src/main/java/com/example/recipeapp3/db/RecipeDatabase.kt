package com.example.recipeapp3.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 5, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract  fun recipeDao():RecipeDao
}