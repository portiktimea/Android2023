package com.tasty.recipesapp.providers

import android.content.Context
import com.tasty.recipesapp.database.RecipeDao
import com.tasty.recipesapp.database.RecipeDatabase
import com.tasty.recipesapp.repository.InstructionsRepository
import com.tasty.recipesapp.repository.RecipesRepository
import com.tasty.recipesapp.repository.TagsRepository

object RepositoryProvider {

    private lateinit var recipeDao: RecipeDao

    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }

    val recipeRepository: RecipesRepository by lazy {
        checkInitialized()
        RecipesRepository(recipeDao)
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }

    val instructionsRepository: InstructionsRepository = InstructionsRepository()
    //val recipeRepository: RecipeRepository = RecipeRepository()
}