package com.tasty.recipesapp.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.providers.RepositoryProvider
import com.tasty.recipesapp.repository.RecipesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfileViewModel() : ViewModel(){

    private val _allRecipes = MutableLiveData<List<NewRecipeModel>>()
    val allRecipes: LiveData<List<NewRecipeModel>>
        get() = _allRecipes
    suspend fun getAllRecipes(context: Context){
        viewModelScope.launch {
            RepositoryProvider.initialize(context)
            val recipes = RepositoryProvider.recipeRepository.getAllRecipes()
            _allRecipes.value = recipes
        }
    }

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.insertRecipe(recipe)
        }
    }

    fun removeRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.deleteRecipe(recipe)
            _allRecipes.value = RepositoryProvider.recipeRepository.getAllRecipes()
        }
    }
}