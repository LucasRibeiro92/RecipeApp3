package com.example.recipeapp3.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp3.adapter.RecipeAdapter
import com.example.recipeapp3.databinding.ActivityMainBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), FilterRecipeFragment.FilterListener/*,
    UpdateRecipeFragment.OnRecipeUpdatedListener*/ {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private val recipeAdapter by lazy { RecipeAdapter() }
    private val dbViewModel: DatabaseViewModel by viewModel<DatabaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        dbViewModel.getAllRecipes()
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding!!.apply {
            ivFilter.setOnClickListener {
                FilterRecipeFragment().show(supportFragmentManager, FilterRecipeFragment().tag)
            }

            btnAddRecipe.setOnClickListener {
                AddRecipeFragment().show(supportFragmentManager, AddRecipeFragment().tag)
            }

            recyclerViewRecipes.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = recipeAdapter
            }

            editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    dbViewModel.searchRecipe(s.toString())
                }
            })
        }
    }

    private fun observeViewModel() {
        dbViewModel.recipesList.observe(this@MainActivity, Observer { recipes ->
            Log.d("TESTEEE", "$recipes")
            if (recipes.isNotEmpty()) {
                Log.d("TESTEEE", "caiu no verdadeiro")
                showEmpty(true)
                recipeAdapter.differ.submitList(recipes)
                recipeAdapter.notifyDataSetChanged()
            } else {
                showEmpty(false)
            }
        })
    }

    private fun showEmpty(isShown: Boolean) {
        binding!!.apply {
            if (isShown) {
                recyclerViewRecipes.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
            } else {
                recyclerViewRecipes.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }


    override fun onFilterApplied(cuisine: String, category: String) {
        dbViewModel.filterRecipes(cuisine, category)
    }

    /*
    override fun onRecipeUpdated() {
        viewModel.getAllRecipes()
    }
    */

}
