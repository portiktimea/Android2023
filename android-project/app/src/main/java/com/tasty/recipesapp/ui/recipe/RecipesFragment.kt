package com.tasty.recipesapp.ui.recipe


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tasty.recipesapp.R


class RecipesFragment : Fragment() {

    private val recipeViewModel : RecipeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //requireActivity().title = "Recipes"

        recipeViewModel.loadInstructionData(requireContext())
        recipeViewModel.instructionModels.observe(viewLifecycleOwner) {  instructions ->
            for (instructionModel in instructions) {
                Log.d("Instructions", instructionModel.toString())
            }
        }
//
//        recipeViewModel.loadTagData(requireContext())
//        recipeViewModel.tagModels.observe(viewLifecycleOwner) {  tags ->
//            for (tagModel in tags) {
//                Log.d("Tags", tagModel.toString())
//            }
//        }

        recipeViewModel.loadRecipeData(requireContext())
        recipeViewModel.recipeModels.observe(viewLifecycleOwner) {  recipes ->
            for (recipeModel in recipes) {
                Log.d("Recipes", recipeModel.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }
}