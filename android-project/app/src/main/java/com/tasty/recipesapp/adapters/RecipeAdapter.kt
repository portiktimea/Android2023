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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.manager.Lifecycle
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class RecipeAdapter(
    var originalRecipes: List<RecipeModel>,

    var selectedFilterPosition: Int = 0
) : RecyclerView.Adapter<RecipeAdapter.MealViewHolder>() {

    private var filteredRecipes: MutableList<RecipeModel> = originalRecipes.toMutableList()
    private var onLikeButtonClickListener: ((RecipeModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = filteredRecipes[position]
        holder.textView.text = meal.name
        val numServings = meal.numServings.toString()
        holder.textViewServings.text = "Servings: $numServings"
        Log.d("Adapter", meal.name)

        holder.imageViewHeart.setOnClickListener{
            holder.imageViewHeart.setImageResource(R.drawable.filled_heart)
            Log.d("liked recipe", meal.name)
            onLikeButtonClickListener?.invoke(meal)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                holder.imageViewHeart.setImageResource(R.drawable.heart)
            }, 200)
        }

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

    fun setOnLikeButtonClickListener(listener: (RecipeModel) -> Unit) {
        onLikeButtonClickListener = listener
    }
    override fun getItemCount(): Int {
        return filteredRecipes.size
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.recipeName)
        val textViewServings: TextView = itemView.findViewById(R.id.servings)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val imageViewHeart: ImageView = itemView.findViewById(R.id.likeButton)
    }

    fun filter(text: String) {
        filteredRecipes.clear()
        if (text.isBlank()) {
            filteredRecipes.addAll(originalRecipes.filter { matchesFilter(it) })
        } else {
            filteredRecipes.addAll(originalRecipes.filter {
                it.name.contains(text, ignoreCase = true) && matchesFilter(it)
            })
        }
        notifyDataSetChanged()
    }

    private fun matchesFilter(recipe: RecipeModel): Boolean {
        return when (selectedFilterPosition) {
            0 -> true
            1 -> recipe.numServings == 2
            2 -> recipe.numServings == 4
            3 -> recipe.numServings == 6
            else -> true
        }
    }

    fun updateList(newList: List<RecipeModel>) {
        originalRecipes = newList
        filteredRecipes.clear()
        filteredRecipes.addAll(newList.filter { matchesFilter(it) })
        notifyDataSetChanged()
    }

    fun sortByOption(option: String) {
        when (option) {
            "name" -> {
                filteredRecipes.sortBy { it.name }
                notifyDataSetChanged()
            }
            "userRatings" -> {
                filteredRecipes.sortByDescending { it.userRatings.score }
                notifyDataSetChanged()
            }
        }
    }

    fun createJsonFromInputs(title: String?, description: String?, pictureUrl: String?, videoUrl: String?, ingredients: List<String>?, instructions: List<String>?): String {
        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("description", description)
        jsonObject.put("pictureUrl", pictureUrl)
        jsonObject.put("videoUrl", videoUrl)
        jsonObject.put("ingredients", JSONArray(ingredients))
        jsonObject.put("instructions", JSONArray(instructions))

        return jsonObject.toString()
    }


}