package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.adapters.NewRecipeAdapter
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        recipeAdapter = NewRecipeAdapter(emptyList())
        recyclerView.adapter = recipeAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.getAllRecipes(requireContext())
        }

        profileViewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.updateData(recipes)
        }

        return view
    }

}