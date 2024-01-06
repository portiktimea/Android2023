package com.tasty.recipesapp.retrofit

import com.tasty.recipesapp.data.dto.InputDTO
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 3aeba23811msh7b6c18fe29e3a38p10b1d2jsned6befed23f4",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): InputDTO

    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: 3aeba23811msh7b6c18fe29e3a38p10b1d2jsned6befed23f4",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipeDetail(
        @Query("id") id: String,
    ): RecipeDTO
}
