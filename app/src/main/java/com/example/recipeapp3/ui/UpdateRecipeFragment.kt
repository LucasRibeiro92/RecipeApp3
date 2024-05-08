package com.example.recipeapp3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp3.databinding.FragmentUpdateRecipeBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class UpdateRecipeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUpdateRecipeBinding? = null
    private val binding get() = _binding
    private val recipe by lazy { RecipeEntity() }
    private val viewModel: DatabaseViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            val recipe = args.getSerializable("recipe") as RecipeEntity
            recipe.let {
                // Faça o que for necessário com o objeto RecipeEntity
                // Por exemplo, configure os campos nos seus elementos de UI
                binding?.edtUpdateTitle?.setText(it.recipeTitle)
                binding?.edtUpdateDesc?.setText(it.recipeIngredient)
            }
        }
    }

    companion object {
        fun newInstance(recipe: RecipeEntity) = UpdateRecipeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("recipe", recipe)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateRecipeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        binding?.apply {
            imgClose.setOnClickListener { dismiss() }

            btnUpdateSave.setOnClickListener {

                val recipeTitle = edtUpdateTitle.text.toString()
                val recipeIngredient = edtUpdateDesc.text.toString()
                val recipeInstruction = edtUpdateDesc.text.toString()
                val recipeImagePath = edtUpdateDesc.text.toString()

                if (recipeTitle.isEmpty()) {
                    Snackbar.make(it, "Title and Description cannot be Empty!", Snackbar.LENGTH_SHORT)
                        .show()
                } else {

                    recipe.recipeId = 0
                    recipe.recipeTitle = recipeTitle
                    recipe.recipeIngredient = recipeIngredient
                    recipe.recipeInstruction = recipeInstruction
                    recipe.recipeImagePath = recipeImagePath

                    viewModel.updateRecipe(recipe)

                    edtUpdateTitle.setText("")
                    edtUpdateDesc.setText("")

                    dismiss()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}