package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.UserRatingsDTO
import com.tasty.recipesapp.data.models.UserRatingsModel
import com.tasty.recipesapp.utils.Mapping.toUserRatingsModelList
import org.json.JSONObject
import java.io.IOException

class UserRatingsRepository : IGenericRepository<UserRatingsModel> {

    override fun getAll(context: Context): List<UserRatingsModel> {
        return readAll(context).toUserRatingsModelList()
    }

    private fun readAll(context: Context): List<UserRatingsDTO> {
        val gson = Gson()
        var userRatingsList = listOf<UserRatingsDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("user_ratings.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val userRatingsArray = jsonObject.getJSONArray("user_ratings")

            val type = object : TypeToken<List<UserRatingsDTO>>() {}.type
            userRatingsList = gson.fromJson(userRatingsArray.toString(), type)

            Log.i("GSON", userRatingsList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return userRatingsList
    }
}
