package com.example.recipeapp3.repository

import com.example.recipeapp3.db.RecipeDao
import com.example.recipeapp3.db.RecipeEntity

class DatabaseRepository(private val dao: RecipeDao) {

    fun saveRecipe(recipe: RecipeEntity) = dao.saveRecipe(recipe)
    fun getAllRecipes() = dao.getAllRecipes()
    fun deleteRecipe(recipe: RecipeEntity) = dao.deleteRecipe(recipe)
    fun updateRecipe(recipe: RecipeEntity) = dao.updateRecipe(recipe)
    fun searchRecipe(textToSearch: String) = dao.searchRecipe(textToSearch)
    fun filterRecipes(cuisine: String, category: String) = dao.filterRecipes(cuisine, category)

}