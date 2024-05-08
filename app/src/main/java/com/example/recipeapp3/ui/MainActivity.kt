package com.example.recipeapp3.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp3.adapter.RecipeAdapter
import com.example.recipeapp3.databinding.ActivityMainBinding
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private val recipeAdapter by lazy { RecipeAdapter() }
    private val viewModel: DatabaseViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        bApply()
    }


    private fun bApply(){
        binding!!.apply {

            btnAddRecipe.setOnClickListener {
                AddRecipeFragment().show(supportFragmentManager, AddRecipeFragment().tag)
            }

            viewModel.getAllRecipes()
            viewModel.recipesList.observe(this@MainActivity) {
                if (it.isNotEmpty()) {
                    showEmpty(true)
                    recipeAdapter.differ.submitList(it)
                    recyclerViewRecipes.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = recipeAdapter
                    }
                } else {
                    showEmpty(false)
                }
            }

        }
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

}