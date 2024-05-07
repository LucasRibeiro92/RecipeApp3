package com.example.recipeapp3.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recipeapp3.R
import com.example.recipeapp3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonAddRecipe: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindings()
        setupListener()
    }

    private fun setupBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonAddRecipe = binding.buttonAddRecipe
    }

    private fun setupListener() {
        buttonAddRecipe.setOnClickListener() {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}