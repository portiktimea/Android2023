package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.CreditDTO
import com.tasty.recipesapp.data.models.CreditModel
import com.tasty.recipesapp.utils.Mapping.toCreditModelList
import org.json.JSONObject
import java.io.IOException

class CreditsRepository : IGenericRepository<CreditModel> {

    override fun getAll(context: Context): List<CreditModel> {
        return readAll(context).toCreditModelList()
    }

    private fun readAll(context: Context): List<CreditDTO> {
        val gson = Gson()
        var creditList = listOf<CreditDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("credits.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val creditsArray = jsonObject.getJSONArray("credits")

            val type = object : TypeToken<List<CreditDTO>>() {}.type
            creditList = gson.fromJson(creditsArray.toString(), type)

            Log.i("GSON", creditList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return creditList
    }
}
