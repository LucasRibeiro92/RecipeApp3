package com.example.recipeapp3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DatabaseRepository) : ViewModel() {

    private val _recipesList = MutableLiveData<List<RecipeEntity>>()
    val recipesList: LiveData<List<RecipeEntity>>
        get() = _recipesList

    fun getAllRecipes() = viewModelScope.launch {
        repository.getAllRecipes().collect() {
            _recipesList.postValue(it)
        }
    }

    fun saveRecipe(recipe: RecipeEntity) = viewModelScope.launch {
        repository.saveRecipe(recipe)
    }

    fun updateRecipe(recipe: RecipeEntity) = viewModelScope.launch {
        repository.updateRecipe(recipe)
    }
}