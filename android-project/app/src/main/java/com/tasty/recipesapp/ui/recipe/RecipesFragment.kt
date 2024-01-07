package com.tasty.recipesapp.ui.recipe


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.adapters.RecipeAdapter


class RecipesFragment : Fragment() {

    private val recipeViewModel: RecipeListViewModel by viewModels()
    private var recipeAdapter: RecipeAdapter = RecipeAdapter(emptyList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = recipeAdapter

        recipeViewModel.getAllRecipesFromApi()
        recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
            for (recipeModel in recipes) {
                Log.d("Recipes", recipeModel.toString())
            }
            recipeAdapter.originalRecipes = recipes
            recipeAdapter.updateList(recipes)
        }

        val searchEditText: EditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                recipeAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) {
                    recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
                        recipeAdapter.updateList(recipes)
                    }
                }
            }
        })

        val spinner: Spinner = view.findViewById(R.id.sortSpinner)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_options_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> recipeAdapter.sortByOption("name")
                    1 -> recipeAdapter.sortByOption("userRatings")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val filterSpinner: Spinner = view.findViewById(R.id.filterSpinner)
        val filterAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_options_array,
            android.R.layout.simple_spinner_item
        )
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = filterAdapter

        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                recipeAdapter.selectedFilterPosition = position
                recipeAdapter.updateList(recipeViewModel.recipeModels.value.orEmpty())
                val searchText = searchEditText.text.toString()
                recipeAdapter.filter(searchText)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

//    override fun onItemClick(id: Int){
//
//    }
}
