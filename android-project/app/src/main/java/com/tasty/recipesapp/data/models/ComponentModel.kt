package com.tasty.recipesapp.data.models

data class ComponentModel(
    val position: Int,
    val measurements: List<MeasurementModel>,
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientModel,
    val id: Int
)
