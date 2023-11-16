package com.tasty.recipesapp.ui.recipe


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapters.RecipeAdapter
import com.tasty.recipesapp.data.models.RecipeModel


class RecipesFragment : Fragment() {

    private val recipeViewModel : RecipeListViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //requireActivity().title = "Recipes"

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recipeAdapter = RecipeAdapter(emptyList(),
            onItemClick = { recipe -> navigateToRecipeDetail(recipe) },
            onDetailsClick = { recipe -> navigateToRecipeDetail(recipe) }
        )
        recyclerView.adapter = recipeAdapter

        recipeViewModel.loadRecipeData(requireContext())
        recipeViewModel.recipeModels.observe(viewLifecycleOwner) {  recipes ->
            for (recipeModel in recipes) {
                Log.d("Recipes", recipeModel.toString())
            }
            recipeAdapter.recipes = recipes
            recipeAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }
}