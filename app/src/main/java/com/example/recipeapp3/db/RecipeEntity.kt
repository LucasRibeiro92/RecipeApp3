package com.example.recipeapp3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipeapp3.utils.Constants.RECIPE_TABLE

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var recipeId:Int =0,
    @ColumnInfo(name = "note_title")
    var recipeTitle:String ="",
    @ColumnInfo(name = "note_ingredient")
    var recipeIngredient:String ="",
    @ColumnInfo(name = "note_instruction")
    var recipeInstruction:String ="",
    @ColumnInfo(name = "note_image_path")
    var recipeImagePath:String =""
)
