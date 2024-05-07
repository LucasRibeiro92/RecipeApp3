package com.example.recipeapp3.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import com.example.recipeapp3.databinding.ActivityAddRecipeBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import org.koin.android.ext.android.inject

class AddRecipeActivity : AppCompatActivity() {

   private val recipeViewModel: DatabaseViewModel by inject()
    private lateinit var binding: ActivityAddRecipeBinding

    private val TAG: String = "CHECKRESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListener()
    }

    private fun setupBinding() {
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListener() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val ingredients = binding.ingredientEditText.text.toString()
            val instructions = binding.instructionEditText.text.toString()

            try {
                // Criar uma instância da Recipe com os dados inseridos
                val recipe = RecipeEntity(recipeTitle = title, recipeIngredient = ingredients, recipeInstruction = instructions)

                // Chamar o método insert da RecipeViewModel para salvar a receita
                recipeViewModel.saveRecipe(recipe)

                // Fechar a atividade e exibir um toast para indicar que a receita foi salva
                finish()
                Toast.makeText(this, "Recipe Saved", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Se houver algum erro, exiba uma mensagem de erro útil
                Toast.makeText(this, "Failed to save recipe: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Failed to save recipe: ${e.message}")
            }
        }
    }


}