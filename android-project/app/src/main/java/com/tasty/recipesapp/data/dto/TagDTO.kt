package com.tasty.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

data class TagDTO(
    val id: Int,
    @SerializedName("display_name")
    val displayName: String,
    val type: String,
    @SerializedName("root_tag_type")
    val rootTagType: String,
    val name: String
)
