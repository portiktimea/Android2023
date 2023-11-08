package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tasty.recipesapp.data.dto.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.InstructionTime

class RecipesViewModel : ViewModel() {

    private var instructionDtoList: List<InstructionDTO>? = null

    private fun fetchInstructionsFromJson(context: Context): List<InstructionDTO> {
        if (instructionDtoList == null) {
            val jsonFileName = "instructions.json"
            val jsonString = context.assets.open(jsonFileName).bufferedReader().use {
                it.readText()
            }

            val gson = Gson()
            instructionDtoList = gson.fromJson(jsonString, Array<InstructionDTO>::class.java).toList()
        }

        return instructionDtoList ?: emptyList()
    }

    fun fetchRecipeInstructions(context: Context): List<InstructionModel> {
        val instructionDtoList = fetchInstructionsFromJson(context)
        val instructionModelList = mutableListOf<InstructionModel>()

        instructionDtoList.forEach { instructionDTO ->
            val instructionTime = InstructionTime(
                instructionDTO.start_time,
                instructionDTO.end_time
            )
            val instructionModel = InstructionModel(
                instructionDTO.id,
                instructionDTO.display_text,
                instructionTime
            )
            instructionModelList.add(instructionModel)
        }
        return instructionModelList
    }

    fun fetchOneRecipeInstruction(context: Context, instructionId: Int): InstructionModel? {
        val instructionDtoList = fetchInstructionsFromJson(context)
        val foundInstructionDTO = instructionDtoList.find { it.id == instructionId }

        return foundInstructionDTO?.let {
            val instructionTime = InstructionTime(it.start_time, it.end_time)
            InstructionModel(it.id, it.display_text, instructionTime)
        }
    }
    fun fetchAllRecipeData(){

    }

    fun fetchInstructionsData(){

    }
}