package com.tasty.recipesapp.data.dto
import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    val tags: List<TagDTO>,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("original_video_url")
    val originalVideoUrl: String,
    @SerializedName("user_ratings")
    val userRatings: UserRatingsDTO,
    val language: String,
    val id: Int,
    val sections: List<SectionDTO>,
    val name: String,
    @SerializedName("video_url")
    val videoUrl: String,
    val nutrition: NutritionDTO,
    val topics: List<TopicDTO>,
    val instructions: List<InstructionDTO>,
    val credits: List<CreditDTO>,
    val country: String,
    val description: String,
    val keywords: String,
    @SerializedName("num_servings")
    val numServings: Int,
)
