package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.TagDTO
import com.tasty.recipesapp.data.models.TagModel
import com.tasty.recipesapp.utils.Mapping.toTagModelList
import org.json.JSONObject
import java.io.IOException

class TagsRepository : IGenericRepository<TagModel> {

    override fun getAll(context: Context): List<TagModel> {
        return readAll(context).toTagModelList()
    }

    private fun readAll(context: Context): List<TagDTO> {
        val gson = Gson()
        var tagList = listOf<TagDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("tags.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            // If there is an extra label
            val jsonObject = JSONObject(jsonString)
            val tagsArray = jsonObject.getJSONArray("tags")

            val type = object : TypeToken<List<TagDTO>>() {}.type
            // If it is simple
            // val tagList = gson.fromJson<List<TagDTO>>(jsonString, type)
            // If with label
            tagList = gson.fromJson(tagsArray.toString(), type)

            Log.i("GSON", tagList.toString())
            // tags.value = tagList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tagList
    }
}
