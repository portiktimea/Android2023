package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.ComponentDTO
import com.tasty.recipesapp.data.models.ComponentModel
import com.tasty.recipesapp.utils.Mapping.toComponentModelList
import org.json.JSONObject
import java.io.IOException

class ComponentsRepository : IGenericRepository<ComponentModel> {

    override fun getAll(context: Context): List<ComponentModel> {
        return readAll(context).toComponentModelList()
    }

    private fun readAll(context: Context): List<ComponentDTO> {
        val gson = Gson()
        var componentList = listOf<ComponentDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("components.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val componentsArray = jsonObject.getJSONArray("components")

            val type = object : TypeToken<List<ComponentDTO>>() {}.type
            componentList = gson.fromJson(componentsArray.toString(), type)

            Log.i("GSON", componentList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return componentList
    }
}
