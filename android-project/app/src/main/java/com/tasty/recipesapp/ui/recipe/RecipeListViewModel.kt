package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.data.models.TagModel
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    // LiveData to hold the list of InstructionModels
    // This should be changed to recipes
    private val _instructionModels = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = _instructionModels

    private val _tagModels = MutableLiveData<List<TagModel>>()
    val tagModels: LiveData<List<TagModel>> = _tagModels

    private val _recipeModels = MutableLiveData<List<RecipeModel>>()
    val recipeModels: LiveData<List<RecipeModel>> = _recipeModels
//
//     Function to load data from the repository
//     Context should be removed
//    fun loadInstructionData(context: Context) {
//        val data = RepositoryProvider.instructionsRepository.getAll(context)
//        _instructionModels.value = data
//    }

//    fun loadTagData(context: Context) {
//        val data = RepositoryProvider.tagsRepository.getAll(context)
//        _tagModels.value = data
//    }

//    fun loadRecipeData(context: Context) {
//        RepositoryProvider.initialize(context)
//        val data = RepositoryProvider.recipeRepository.getAll(context)
//        _recipeModels.value = data
//    }

    fun getAllRecipesFromApi() {
        viewModelScope.launch {
            val recipes = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "20")
            _recipeModels.value = recipes
            Log.d("recipesApi", recipes.toString())
            recipes.forEach {
                Log.d("RECIPE_API", it.toString())
            }
        }
    }

}