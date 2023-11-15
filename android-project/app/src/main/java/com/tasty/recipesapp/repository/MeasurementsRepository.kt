package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.MeasurementDTO
import com.tasty.recipesapp.data.models.MeasurementModel
import com.tasty.recipesapp.utils.Mapping.toMeasurementModelList
import org.json.JSONObject
import java.io.IOException

class MeasurementsRepository : IGenericRepository<MeasurementModel> {

    override fun getAll(context: Context): List<MeasurementModel> {
        return readAll(context).toMeasurementModelList()
    }

    private fun readAll(context: Context): List<MeasurementDTO> {
        val gson = Gson()
        var measurementList = listOf<MeasurementDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("measurements.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val measurementsArray = jsonObject.getJSONArray("measurements")

            val type = object : TypeToken<List<MeasurementDTO>>() {}.type
            measurementList = gson.fromJson(measurementsArray.toString(), type)

            Log.i("GSON", measurementList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return measurementList
    }
}
