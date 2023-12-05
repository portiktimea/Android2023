package com.tasty.recipesapp.ui.recipe

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.tasty.recipesapp.data.models.NewRecipeModel
import com.tasty.recipesapp.databinding.FragmentNewRecipeDetailBinding
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class NewRecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root


        lifecycleScope.launch {
            val args = this@NewRecipeDetailFragment.arguments
            val inputId = args?.get("recipe") as? Long

            inputId?.let { id ->
                val recipe = RepositoryProvider.recipeRepository.getRecipeById(id)
                val textViewTitle = binding.textViewTitle
                val imageViewRecipe = binding.imageViewRecipe
                val textViewDescription = binding.textViewDescription
                val textViewIngredients = binding.textViewIngredients
                val textViewInstructions = binding.textViewInstructions
                val textViewVideoUrl = binding.textViewVideoUrl

                Glide.with(this@NewRecipeDetailFragment).load(recipe?.pictureUrl).into(imageViewRecipe)
                textViewTitle.text = recipe?.title
                textViewDescription.text = recipe?.description
                textViewIngredients.text = recipe?.ingredients?.joinToString(separator = "\n") { "- $it" }
                textViewInstructions.text = recipe?.instructions?.joinToString(separator = "\n") { "- $it" }
                textViewVideoUrl.text = recipe?.videoUrl
            }
        }

        return view
    }
}

