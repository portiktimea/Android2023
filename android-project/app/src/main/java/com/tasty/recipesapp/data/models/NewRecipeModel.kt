package com.tasty.recipesapp.data.models

data class NewRecipeModel(
    val title: String?,
    val description: String?,
    val pictureUrl: String?,
    val videoUrl: String?,
    val ingredients: List<String>?,
    val instructions: List<String>?
)
