package com.tasty.recipesapp.adapters

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class RecipeAdapter(
    var originalRecipes: List<RecipeModel>
) : RecyclerView.Adapter<RecipeAdapter.MealViewHolder>() {

    private var filteredRecipes: MutableList<RecipeModel> = originalRecipes.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = filteredRecipes[position]
        holder.textView.text = meal.name
        Log.d("Adapter", meal.name)

        val executor = Executors.newSingleThreadExecutor()

        val handler = Handler(Looper.getMainLooper())

        executor.execute {

            val imageURL = meal.thumbnailUrl

            try {
                val url = URL(imageURL)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(input)

                handler.post {
                    holder.imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredRecipes.size
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView6)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    fun filter(text: String) {
        filteredRecipes.clear()
        if (text.isBlank()) {
            filteredRecipes.addAll(originalRecipes)
        } else {
            for (item in originalRecipes) {
                if (item.name.contains(text, ignoreCase = true)) {
                    filteredRecipes.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateList(newList: List<RecipeModel>) {
        originalRecipes = newList
        filteredRecipes.clear()
        filteredRecipes.addAll(newList)
        notifyDataSetChanged()
    }
}