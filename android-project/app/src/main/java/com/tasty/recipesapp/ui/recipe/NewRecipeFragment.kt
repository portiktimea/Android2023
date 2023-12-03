package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.database.RecipeDao
import com.tasty.recipesapp.database.RecipeDatabase
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.providers.RepositoryProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.tasty.recipesapp.data.dto.NewRecipeDTO
import kotlinx.coroutines.launch

class NewRecipeFragment : Fragment(){

    private lateinit var binding : FragmentNewRecipeBinding
    private lateinit var recipeDao: RecipeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        RepositoryProvider.initialize(context)
        recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        val linearLayoutIngredient = binding.linearLayout2
        val linearLayoutInstruction = binding.linearLayout3

        val addIngredientButton = binding.addIngredientButton
        val addInstructionButton = binding.addInstructionButton

        addIngredientButton.setOnClickListener {
            addNewEditText(linearLayoutIngredient, "Enter ingredient")
        }

        addInstructionButton.setOnClickListener {
            addNewEditText(linearLayoutInstruction, "Enter instruction")
        }

        val saveRecipeButton = binding.saveRecipeButton
        saveRecipeButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                saveRecipeToDatabase(binding)
            }
        }

        return binding.root
    }

    private suspend fun saveRecipeToDatabase(binding: FragmentNewRecipeBinding) {
        val title = binding.recipeTitle.text.toString()
        val description = binding.recipeDescription.text.toString()
        val pictureURL = binding.recipePictureURL.text.toString()
        val videoURL = binding.recipeVideoURL.text.toString()

        val ingredients = mutableListOf<String>()
        ingredients.add(binding.firstIngredient.text.toString())
        ingredients.add(binding.secondIngredient.text.toString())
        ingredients.add(binding.thirdIngredient.text.toString())

        val instructions = mutableListOf<String>()
        instructions.add(binding.firstInstruction.text.toString())
        instructions.add(binding.secondInstruction.text.toString())
        instructions.add(binding.thirdInstruction.text.toString())

        val recipe = NewRecipeDTO(title, description, pictureURL, videoURL, ingredients, instructions)
        val gson = Gson()
        val recipeEntity = RecipeEntity(
            json = gson.toJson(recipe)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            RepositoryProvider.recipeRepository.insertRecipe(recipeEntity)
        }
    }

//    private fun createJsonFromInputs(title: String, description: String, pictureUrl: String, videoUrl: String, ingredients: List<String>, instructions: List<String>): String {
//        val jsonObject = JSONObject()
//        jsonObject.put("title", title)
//        jsonObject.put("description", description)
//        jsonObject.put("pictureUrl", pictureUrl)
//        jsonObject.put("videoUrl", videoUrl)
//        jsonObject.put("ingredients", JSONArray(ingredients))
//        jsonObject.put("instructions", JSONArray(instructions))
//
//        return jsonObject.toString()
//    }

    private fun addNewEditText(linearLayout: LinearLayout, hint: String) {
        val editText = EditText(context)
        editText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editText.hint = hint
        editText.textSize = 16f

        linearLayout.addView(editText)
    }

}