package com.tasty.recipesapp.data.dto

data class SectionDTO(
    val components: List<ComponentDTO>,
    val name: String?,
    val position: Int
)