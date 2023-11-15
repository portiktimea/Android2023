package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.IngredientDTO
import com.tasty.recipesapp.data.models.IngredientModel
import com.tasty.recipesapp.utils.Mapping.toIngredientModelList
import org.json.JSONObject
import java.io.IOException

class IngredientsRepository : IGenericRepository<IngredientModel> {

    override fun getAll(context: Context): List<IngredientModel> {
        return readAll(context).toIngredientModelList()
    }

    private fun readAll(context: Context): List<IngredientDTO> {
        val gson = Gson()
        var ingredientList = listOf<IngredientDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("ingredients.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val ingredientsArray = jsonObject.getJSONArray("ingredient")

            val type = object : TypeToken<List<IngredientDTO>>() {}.type
            ingredientList = gson.fromJson(ingredientsArray.toString(), type)

            Log.i("GSON", ingredientList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ingredientList
    }
}
