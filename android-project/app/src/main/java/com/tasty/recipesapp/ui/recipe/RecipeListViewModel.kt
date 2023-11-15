package com.tasty.recipesapp.ui.recipe

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.data.models.TagModel
import com.tasty.recipesapp.providers.RepositoryProvider

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
    fun loadInstructionData(context: Context) {
        val data = RepositoryProvider.instructionsRepository.getAll(context)
        _instructionModels.value = data
    }

//    fun loadTagData(context: Context) {
//        val data = RepositoryProvider.tagsRepository.getAll(context)
//        _tagModels.value = data
//    }

    fun loadRecipeData(context: Context) {
        val data = RepositoryProvider.recipesRepository.getAll(context)
        _recipeModels.value = data
    }
}