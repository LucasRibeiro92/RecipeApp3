package com.example.recipeapp3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp3.R
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
    private val viewModel: DatabaseViewModel by inject()

    companion object {
        fun newInstance() = UpdateRecipeFragment()

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
        val bundle = arguments
        // Check if the Bundle is not null
        if (bundle != null) {
            // Retrieve data class object from the Bundle using the key
            val recipe = bundle.getSerializable("recipe") as RecipeEntity

            if(recipe.recipeId == 0 ||recipe.recipeTitle == null) {
                dismiss()
            }
            // Now you have the data object, you can use it in your Fragment
            binding?.apply {
                binding?.edtUpdateTitle?.setText(recipe.recipeTitle)
                binding?.edtUpdateIngredient?.setText(recipe.recipeIngredient)
                binding?.edtUpdateInstruction?.setText(recipe.recipeInstruction)
                binding?.edtUpdateImgPath?.setText(recipe.recipeImagePath)
                binding?.edtUpdateTimeCook?.setText(recipe.recipeTime)
                binding?.spnUpdateRecipeCuisine?.setSelection(getCuisinePosition(recipe.recipeCuisine))
                binding?.spnUpdateRecipeCategory?.setSelection(getCategoryPosition(recipe.recipeCategory))

                imgClose.setOnClickListener { dismiss() }

                btnUpdateSave.setOnClickListener {

                    val recipeTitle = edtUpdateTitle.text.toString()
                    val recipeIngredient = edtUpdateIngredient.text.toString()
                    val recipeInstruction = edtUpdateInstruction.text.toString()
                    val recipeImagePath = edtUpdateImgPath.text.toString()
                    val recipeTimeToCook = edtUpdateTimeCook.text.toString()
                    val recipeCuisine = spnUpdateRecipeCuisine.selectedItem.toString()
                    val recipeCategory = spnUpdateRecipeCategory.selectedItem.toString()

                    if (recipeTitle.isEmpty()) {
                        Snackbar.make(it, "Title cannot be Empty!", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {

                        recipe.recipeTitle = recipeTitle
                        recipe.recipeIngredient = recipeIngredient
                        recipe.recipeInstruction = recipeInstruction
                        recipe.recipeImagePath = recipeImagePath
                        recipe.recipeTime = recipeTimeToCook
                        recipe.recipeCuisine = recipeCuisine
                        recipe.recipeCategory = recipeCategory

                        viewModel.updateRecipe(recipe)

                        edtUpdateTitle.setText("")
                        edtUpdateIngredient.setText("")
                        edtUpdateInstruction.setText("")
                        edtUpdateImgPath.setText("")
                        edtUpdateTimeCook.setText("")
                        spnUpdateRecipeCuisine.setSelection(0)
                        spnUpdateRecipeCategory.setSelection(0)

                        dismiss()
                    }
                }
            }
        } else {
            dismiss()
        }

    }

    private fun getCuisinePosition(recipeCuisine: String): Int {
        val cuisineOptions = resources.getStringArray(R.array.cuisine_options)
        return cuisineOptions.indexOf(recipeCuisine)
    }

    private fun getCategoryPosition(recipeCategory: String): Int {
        val categoryOptions = resources.getStringArray(R.array.category_options)
        return categoryOptions.indexOf(recipeCategory)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}