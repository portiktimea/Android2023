package com.tasty.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

data class UnitDTO(
    @SerializedName("display_singular")
    val displaySingular: String,
    val abbreviation: String,
    val system: String,
    val name: String,
    @SerializedName("display_plural")
    val displayPlural: String
)
