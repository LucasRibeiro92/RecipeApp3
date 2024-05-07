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

}