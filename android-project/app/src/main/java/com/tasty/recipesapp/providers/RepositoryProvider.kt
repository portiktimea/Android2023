package com.tasty.recipesapp.providers

import com.tasty.recipesapp.repository.InstructionsRepository

object RepositoryProvider {
    val instructionsRepository: InstructionsRepository = InstructionsRepository()
}