package com.example.recipeapp3.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

            // Configurando o RecyclerView
            recyclerViewRecipes.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = recipeAdapter
            }

            // Configurando o EditText para a pesquisa
            editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filterRecipes(s.toString())
                }
            })

            // Observando a lista de receitas no ViewModel
            viewModel.getAllRecipes()
            viewModel.recipesList.observe(this@MainActivity) {
                if (it.isNotEmpty()) {
                    showEmpty(true)
                    recipeAdapter.differ.submitList(it)
                } else {
                    showEmpty(false)
                }
            }
        }
    }

    // Método para filtrar a lista de receitas com base na consulta de pesquisa
    private fun filterRecipes(query: String) {
        val filteredList = viewModel.recipesList.value?.filter {
            it.recipeTitle.contains(query, ignoreCase = true) || it.recipeIngredient.contains(query, ignoreCase = true)
        }
        recipeAdapter.differ.submitList(filteredList)
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