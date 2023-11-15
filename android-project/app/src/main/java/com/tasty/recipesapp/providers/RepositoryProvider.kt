package com.tasty.recipesapp.providers

import com.tasty.recipesapp.repository.InstructionsRepository
import com.tasty.recipesapp.repository.RecipesRepository
import com.tasty.recipesapp.repository.TagsRepository

object RepositoryProvider {
    val instructionsRepository: InstructionsRepository = InstructionsRepository()
    val tagsRepository: TagsRepository = TagsRepository()
    val recipesRepository: RecipesRepository = RecipesRepository()
}