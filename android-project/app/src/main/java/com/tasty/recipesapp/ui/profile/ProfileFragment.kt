package com.tasty.recipesapp.ui.profile

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.providers.RepositoryProvider


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

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
        return view
    }

}