package com.example.recipeapp3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recipeapp3.utils.Constants.RECIPE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecipe(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM $RECIPE_TABLE ORDER BY recipeId DESC")
    fun getAllRecipes(): Flow<MutableList<RecipeEntity>>

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)

    @Update
    fun updateRecipe(recipe: RecipeEntity)

    // Consulta para pesquisar receitas com base no título
    @Query("SELECT * FROM $RECIPE_TABLE WHERE recipe_title LIKE '%' || :textToSearch || '%' ORDER BY recipeId DESC")
    fun searchRecipe(textToSearch: String): Flow<MutableList<RecipeEntity>>

    // Consulta para aplicar filtros com base na cozinha e categoria
    @Query("SELECT * FROM $RECIPE_TABLE WHERE recipe_cuisine = :cuisine AND recipe_category = :category ORDER BY recipeId DESC")
    fun filterRecipes(cuisine: String, category: String): Flow<MutableList<RecipeEntity>>

}