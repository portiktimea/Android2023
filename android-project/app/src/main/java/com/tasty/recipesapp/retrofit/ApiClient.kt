package com.tasty.recipesapp.retrofit

object ApiClient {
    val apiService: RecipeService by lazy {
        RecipeApiClient.retrofit.create(RecipeService::class.java)
    }
}