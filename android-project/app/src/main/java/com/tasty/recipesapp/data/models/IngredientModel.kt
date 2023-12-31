package com.tasty.recipesapp.data.models

data class IngredientModel(
    val id: Int,
    val displaySingular: String,
    val updatedAt: Long,
    val name: String,
    val createdAt: Long,
    val displayPlural: String
)
