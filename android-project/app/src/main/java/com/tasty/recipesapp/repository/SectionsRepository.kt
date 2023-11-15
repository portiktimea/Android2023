package com.tasty.recipesapp.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.SectionDTO
import com.tasty.recipesapp.data.models.SectionModel
import com.tasty.recipesapp.utils.Mapping.toSectionModelList
import org.json.JSONObject
import java.io.IOException

class SectionsRepository : IGenericRepository<SectionModel> {

    override fun getAll(context: Context): List<SectionModel> {
        return readAll(context).toSectionModelList()
    }

    private fun readAll(context: Context): List<SectionDTO> {
        val gson = Gson()
        var sectionList = listOf<SectionDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("sections.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val sectionsArray = jsonObject.getJSONArray("sections")

            val type = object : TypeToken<List<SectionDTO>>() {}.type
            sectionList = gson.fromJson(sectionsArray.toString(), type)

            Log.i("GSON", sectionList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sectionList
    }
}
