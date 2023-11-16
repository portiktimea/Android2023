package com.tasty.recipesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel

class RecipeAdapter(
    var recipes: List<RecipeModel>,
    private val onItemClick: (RecipeModel) -> Unit,
    private val onDetailsClick: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipes, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

//        holder.itemView.setOnClickListener { onItemClick(recipe) }
//        holder.itemView.findViewById<Button>(R.id.detailsButton)
//            .setOnClickListener { onDetailsClick(recipe) }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeNameTextView: TextView = itemView.findViewById(R.id.recipeNameTextView)

        fun bind(recipe: RecipeModel) {
            recipeNameTextView.text = recipe.name ?: ""
        }
    }


}

