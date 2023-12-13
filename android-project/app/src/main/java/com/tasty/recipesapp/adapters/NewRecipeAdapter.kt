package com.tasty.recipesapp.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.NewRecipeModel
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class NewRecipeAdapter(
    var recipes: List<NewRecipeModel>,
    private val onRecipeLongClicked: (NewRecipeModel) -> Unit,
    private val onClickListener: (NewRecipeModel) -> Unit
) : RecyclerView.Adapter<NewRecipeAdapter.NewRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false)
        return NewRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewRecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.textView.text = recipe.title

        val executor = Executors.newSingleThreadExecutor()

        val handler = Handler(Looper.getMainLooper())

        executor.execute {

            val imageURL = recipe.pictureUrl

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

        holder.itemView.setOnLongClickListener{
            onRecipeLongClicked.invoke(recipe)
            true
        }

        holder.itemView.setOnClickListener {
            onClickListener.invoke(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun updateData(newData: List<NewRecipeModel>) {
        recipes = newData
        notifyDataSetChanged()
    }

    class NewRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.findViewById(R.id.textView6)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }

}
