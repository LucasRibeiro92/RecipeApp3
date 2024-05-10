package com.example.recipeapp3.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp3.R
import com.example.recipeapp3.databinding.FragmentAddRecipeBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddRecipeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding
    private val recipe by lazy { RecipeEntity() }
    private val viewModel: DatabaseViewModel by sharedViewModel()

    private var recipeTitle = ""
    private var recipeIngredient = ""
    private var recipeInstruction = ""
    private var recipeImagePath = ""
    private var recipeTime = ""
    private var recipeCuisine = ""
    private var recipeCategory = ""

    companion object {
        fun newInstance() = AddRecipeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRecipeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        binding?.apply {
            imgClose.setOnClickListener { dismiss() }

            btnSave.setOnClickListener {

                recipeTitle = edtTitle.text.toString()
                recipeIngredient = edtIngredient.text.toString()
                recipeInstruction = edtInstruction.text.toString()
                recipeImagePath = edtImgPath.text.toString()
                recipeTime = edtTimeCook.text.toString()
                recipeCuisine = spnRecipeCuisine.selectedItem.toString()
                recipeCategory = spnRecipeCategory.selectedItem.toString()

                if (recipeTitle.isEmpty()) {
                    Snackbar.make(it, "Title and Description cannot be Empty!", Snackbar.LENGTH_SHORT)
                        .show()
                } else {

                    recipe.recipeId = 0
                    recipe.recipeTitle = recipeTitle
                    recipe.recipeIngredient = recipeIngredient
                    recipe.recipeInstruction = recipeInstruction
                    recipe.recipeImagePath = recipeImagePath
                    recipe.recipeTime = recipeTime
                    recipe.recipeCuisine = recipeCuisine
                    recipe.recipeCategory = recipeCategory

                    viewModel.saveRecipe(recipe)

                    edtTitle.setText("")
                    edtIngredient.setText("")
                    edtInstruction.setText("")
                    edtImgPath.setText("")
                    edtTimeCook.setText("")
                    spnRecipeCuisine.setSelection(0)
                    spnRecipeCategory.setSelection(0)

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