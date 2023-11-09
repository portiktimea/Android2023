package com.tasty.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

data class ComponentDTO(
    val position: Int,
    val measurements: List<MeasurementDTO>,
    @SerializedName("raw_text")
    val rawText: String,
    @SerializedName("extra_comment")
    val extraComment: String,
    val ingredient: IngredientDTO,
    val id: Int
)
