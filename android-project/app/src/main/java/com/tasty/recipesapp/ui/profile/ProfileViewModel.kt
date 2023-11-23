package com.tasty.recipesapp.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.providers.RepositoryProvider.recipeRepository
import com.tasty.recipesapp.repository.RecipesRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val recipeRepository: RecipesRepository) : ViewModel(){

    suspend fun getAllRecipes() = recipeRepository.getAllRecipes()

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            recipeRepository.insertRecipe(recipe)
        }
    }

    fun removeRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            recipeRepository.deleteRecipe(recipe)
        }
    }
}