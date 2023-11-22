package com.tasty.recipesapp.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import java.util.concurrent.Executors

class RecipeAdapter(
    var recipes: List<RecipeModel>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.textView.text = recipe.name

        val executor = Executors.newSingleThreadExecutor()

        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null
        executor.execute {

            val imageURL = recipe.thumbnailUrl

            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    holder.imageView.setImageBitmap(image)
                }
            }

            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.findViewById(R.id.textView)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }

}

