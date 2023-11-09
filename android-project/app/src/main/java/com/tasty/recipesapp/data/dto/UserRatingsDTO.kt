package com.tasty.recipesapp.data.dto

import com.google.gson.annotations.SerializedName
data class UserRatingsDTO(
    @SerializedName("count_positive")
    val countPositive: Int,
    val score: Double,
    @SerializedName("count_negative")
    val countNegative: Int
)
