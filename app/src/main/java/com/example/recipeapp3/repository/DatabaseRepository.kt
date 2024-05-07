package com.example.recipeapp3.repository

import com.example.recipeapp3.db.RecipeDao
import com.example.recipeapp3.db.RecipeEntity

class DatabaseRepository(private val dao: RecipeDao) {

    fun saveRecipe(note: RecipeEntity) = dao.saveRecipe(note)
    fun getAllRecipes() = dao.getAllRecipes()

}