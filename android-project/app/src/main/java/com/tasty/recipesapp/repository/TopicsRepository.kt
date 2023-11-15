package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.TopicDTO
import com.tasty.recipesapp.data.models.TopicModel
import com.tasty.recipesapp.utils.Mapping.toTopicModelList
import org.json.JSONObject
import java.io.IOException

class TopicsRepository : IGenericRepository<TopicModel> {

    override fun getAll(context: Context): List<TopicModel> {
        return readAll(context).toTopicModelList()
    }

    private fun readAll(context: Context): List<TopicDTO> {
        val gson = Gson()
        var topicList = listOf<TopicDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("topics.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val topicsArray = jsonObject.getJSONArray("topics")

            val type = object : TypeToken<List<TopicDTO>>() {}.type
            topicList = gson.fromJson(topicsArray.toString(), type)

            Log.i("GSON", topicList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return topicList
    }
}
