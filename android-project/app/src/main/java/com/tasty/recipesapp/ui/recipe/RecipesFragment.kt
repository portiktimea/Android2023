package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [RecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Recipes"
        viewModel = ViewModelProvider(this)[RecipesViewModel::class.java]

        val instructionList = viewModel.fetchRecipeInstructions(requireContext())
        Log.i("Instructions", instructionList.toString())

        val instructionId = 43383
        val oneInstruction = viewModel.fetchOneRecipeInstruction(requireContext(), instructionId)

        oneInstruction?.let {
            Log.i("OneInstruction", it.toString())
        } ?: Log.i("OneInstruction", "Instruction not found")
    }


}