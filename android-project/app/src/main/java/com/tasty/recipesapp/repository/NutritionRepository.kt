package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.NutritionDTO
import com.tasty.recipesapp.data.models.NutritionModel
import com.tasty.recipesapp.utils.Mapping.toNutritionModelList
import org.json.JSONObject
import java.io.IOException

class NutritionRepository : IGenericRepository<NutritionModel> {

    override fun getAll(context: Context): List<NutritionModel> {
        return readAll(context).toNutritionModelList()
    }

    private fun readAll(context: Context): List<NutritionDTO> {
        val gson = Gson()
        var nutritionList = listOf<NutritionDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("nutrition.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val nutritionArray = jsonObject.getJSONArray("nutrition")

            val type = object : TypeToken<List<NutritionDTO>>() {}.type
            nutritionList = gson.fromJson(nutritionArray.toString(), type)

            Log.i("GSON", nutritionList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return nutritionList
    }
}
