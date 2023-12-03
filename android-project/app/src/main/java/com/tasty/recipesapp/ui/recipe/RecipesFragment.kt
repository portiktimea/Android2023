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
import com.tasty.recipesapp.adapters.NewRecipeAdapter
import com.tasty.recipesapp.adapters.RecipeAdapter
import com.tasty.recipesapp.data.models.RecipeModel


class RecipesFragment : Fragment() {

    private val recipeViewModel: RecipeListViewModel by viewModels()
    private var recipeAdapter: RecipeAdapter = RecipeAdapter(emptyList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = recipeAdapter

        recipeViewModel.loadRecipeData(requireContext())
        recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
            for (recipeModel in recipes) {
                Log.d("Recipes", recipeModel.toString())
            }
            recipeAdapter.recipes = recipes
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

//    override fun onItemClick(id: Int){
//
//    }
}
