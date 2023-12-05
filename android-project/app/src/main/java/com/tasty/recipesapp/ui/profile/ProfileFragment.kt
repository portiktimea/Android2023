package com.tasty.recipesapp.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapters.NewRecipeAdapter
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.database.RecipeEntity
import com.tasty.recipesapp.providers.RepositoryProvider
import com.tasty.recipesapp.ui.recipe.NewRecipeDetailFragment
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var recipeAdapter: NewRecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(com.tasty.recipesapp.R.layout.fragment_profile, container, false)
        val fabAddRecipe = view.findViewById<FloatingActionButton>(com.tasty.recipesapp.R.id.fabAddRecipe)
        fabAddRecipe.setOnClickListener { NavHostFragment.findNavController(this).navigate(com.tasty.recipesapp.R.id.action_profileFragment_to_newRecipeFragment) }

        val recyclerView: RecyclerView = view.findViewById(com.tasty.recipesapp.R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        recipeAdapter = NewRecipeAdapter(emptyList(),
            { recipe -> confirmDeleteRecipe(recipe) },
            { recipe -> onRecipeClicked(recipe) }
        )
        
        recyclerView.adapter = recipeAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.getAllRecipes(requireContext())
        }

        profileViewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.updateData(recipes)
        }

        return view
    }

    private fun onRecipeClicked(recipe: NewRecipeModel) {
        //val action = ProfileFragmentDirections.actionProfileFragmentToNewRecipeDetailFragment(recipe)
        //findNavController().navigate(action)
        val bundle = Bundle()
        bundle.putLong("recipe", recipe.id)
        val detailFragment = NewRecipeDetailFragment()
        detailFragment.arguments = bundle
        NavHostFragment.findNavController(this).navigate(R.id.action_profileFragment_to_newRecipeDetailFragment, bundle)
    }

    private fun confirmDeleteRecipe(recipe: NewRecipeModel) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Recept törlése")
            setMessage("Biztosan törölni szeretné ezt a receptet?")
            setPositiveButton("Igen") { _, _ ->
                deleteRecipe(recipe)
            }
            setNegativeButton("Mégsem") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun deleteRecipe(recipe: NewRecipeModel) {
        val recipeEntity = convertToRecipeEntity(recipe)

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.removeRecipe(recipeEntity)
            profileViewModel.getAllRecipes(requireContext())
        }
    }

    private fun convertToRecipeEntity(recipe: NewRecipeModel): RecipeEntity {
        val gson = Gson()
        return RecipeEntity(
            internalId = recipe.id,
            json = gson.toJson(recipe)
        )
    }

}