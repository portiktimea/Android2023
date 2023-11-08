package com.tasty.recipesapp.data.dto

data class TagDTO(
    val id: Int,
    val display_name: String,
    val type: String,
    val root_tag_type: String,
    val name: String
)
