package com.tasty.recipesapp.data.models

data class SectionModel(
    val components: List<ComponentModel>,
    val name: String?,
    val position: Int
)
