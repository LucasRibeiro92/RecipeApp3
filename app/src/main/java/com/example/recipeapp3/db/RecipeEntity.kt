package com.example.recipeapp3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp3.utils.Constants.RECIPE_TABLE
import java.io.Serializable

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var recipeId: Int = 0,
    @ColumnInfo(name = "recipe_title")
    var recipeTitle: String = "",
    @ColumnInfo(name = "recipe_ingredient")
    var recipeIngredient: String = "",
    @ColumnInfo(name = "recipe_instruction")
    var recipeInstruction: String = "",
    @ColumnInfo(name = "recipe_image_path")
    var recipeImagePath: String = ""
) : Serializable


