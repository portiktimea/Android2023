package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.UnitDTO
import com.tasty.recipesapp.data.models.UnitModel
import com.tasty.recipesapp.utils.Mapping.toUnitModelList
import org.json.JSONObject
import java.io.IOException

class UnitRepository : IGenericRepository<UnitModel> {

    override fun getAll(context: Context): List<UnitModel> {
        return readAll(context).toUnitModelList()
    }

    private fun readAll(context: Context): List<UnitDTO> {
        val gson = Gson()
        var unitList = listOf<UnitDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("units.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val unitsArray = jsonObject.getJSONArray("unit")

            val type = object : TypeToken<List<UnitDTO>>() {}.type
            unitList = gson.fromJson(unitsArray.toString(), type)

            Log.i("GSON", unitList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return unitList
    }
}
