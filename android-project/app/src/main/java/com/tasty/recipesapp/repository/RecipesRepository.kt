package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.utils.Mapping.toRecipeModelList
import org.json.JSONObject
import java.io.IOException

class RecipesRepository : IGenericRepository<RecipeModel> {

    override fun getAll(context: Context): List<RecipeModel> {
        return readAll(context).toRecipeModelList()
    }

    fun readAll(context: Context): List<RecipeDTO> {
        val gson = Gson()
        var recipeList = listOf<RecipeDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("recipes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val recipesArray = jsonObject.getJSONArray("results")

            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            recipeList = gson.fromJson(recipesArray.toString(), type)

            Log.i("GSON", recipeList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }
}
