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
    var recipeImagePath: String = "",
    @ColumnInfo(name = "recipe_is_favorite")
    var recipeIsFavorite: Boolean = false,
    @ColumnInfo(name = "recipe_cuisine")
    var recipeCuisine: String = "",
    @ColumnInfo(name = "recipe_category")
    var recipeCategory: String = "",
    @ColumnInfo(name = "recipe_time")
    var recipeTime: String = ""
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RecipeEntity) return false

        if (recipeId != other.recipeId) return false
        if (recipeTitle != other.recipeTitle) return false
        if (recipeIngredient != other.recipeIngredient) return false
        if (recipeInstruction != other.recipeInstruction) return false
        if (recipeImagePath != other.recipeImagePath) return false
        if (recipeIsFavorite != other.recipeIsFavorite) return false
        if (recipeCuisine != other.recipeCuisine) return false
        if (recipeCategory != other.recipeCategory) return false
        if (recipeTime != other.recipeTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = recipeId
        result = 31 * result + recipeTitle.hashCode()
        result = 31 * result + recipeIngredient.hashCode()
        result = 31 * result + recipeInstruction.hashCode()
        result = 31 * result + recipeImagePath.hashCode()
        result = 31 * result + recipeIsFavorite.hashCode()
        result = 31 * result + recipeCuisine.hashCode()
        result = 31 * result + recipeCategory.hashCode()
        result = 31 * result + recipeTime.hashCode()
        return result
    }
}


