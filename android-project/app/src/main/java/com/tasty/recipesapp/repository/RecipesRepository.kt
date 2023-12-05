package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.NewRecipeDTO
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.database.RecipeDao
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.utils.Mapping.toModel
import com.tasty.recipesapp.utils.Mapping.toRecipeModelList
import org.json.JSONObject
import java.io.IOException

class RecipesRepository(private val recipeDao: RecipeDao) : IGenericRepository<RecipeModel> {
    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun getAllRecipes(): List<NewRecipeModel> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            val gson = Gson()
            gson.fromJson(jsonObject.toString(), NewRecipeDTO::class.java).toModel()
        }
    }

    suspend fun getRecipeById(recipeId: Long): NewRecipeModel? {
        val recipeEntity = recipeDao.getRecipeById(recipeId)
        return recipeEntity?.let {
            val jsonObject = JSONObject(it.json)
            jsonObject.put("id", it.internalId)
            val gson = Gson()
            gson.fromJson(jsonObject.toString(), NewRecipeDTO::class.java).toModel()
        }
    }

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
