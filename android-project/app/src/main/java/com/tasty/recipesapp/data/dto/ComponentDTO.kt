package com.tasty.recipesapp.data.dto

data class ComponentDTO(
    val position: Int,
    val measurements: List<MeasurementDTO>,
    val raw_text: String,
    val extra_comment: String,
    val ingredient: IngredientDTO,
    val id: Int
)
