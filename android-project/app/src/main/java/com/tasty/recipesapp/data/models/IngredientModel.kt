package com.tasty.recipesapp.data.models

data class IngredientModel(
    val id: Int,
    val display_singular: String,
    val updated_at: Long,
    val name: String,
    val created_at: Long,
    val display_plural: String
)
