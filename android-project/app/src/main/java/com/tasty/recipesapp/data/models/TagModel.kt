package com.tasty.recipesapp.data.models

data class TagModel(
    val id: Int,
    val displayName: String,
    val type: String,
    val rootTagType: String,
    val name: String
)
