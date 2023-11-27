package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding

class NewRecipeFragment : Fragment(){

    private lateinit var binding : FragmentNewRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }
}