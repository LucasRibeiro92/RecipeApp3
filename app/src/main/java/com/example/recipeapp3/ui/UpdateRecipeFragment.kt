package com.example.recipeapp3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp3.R
import com.example.recipeapp3.databinding.FragmentAddRecipeBinding
import com.example.recipeapp3.databinding.FragmentUpdateRecipeBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.viewmodel.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class UpdateRecipeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentUpdateRecipeBinding? = null
    private val binding get() = _binding

    private val recipe by lazy { RecipeEntity() }
    private val viewModel: DatabaseViewModel by inject()

    private var recipeTitle = ""
    private var recipeIngredient = ""
    private var recipeInstruction = ""
    private var recipeImagePath = ""

    companion object {
        fun newInstance(recipe: RecipeEntity) = UpdateRecipeFragment().apply {
            arguments = Bundle().apply {
                putParcelable("recipe", recipe)
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

            recipe?.let {
                edtTitle.setText(it.recipeTitle)
                edtDesc.setText(it.recipeIngredient)
                // Preencher outros campos conforme necessário
            }

            imgClose.setOnClickListener { dismiss() }

            btnSave.setOnClickListener {

                recipeTitle = edtTitle.text.toString()
                recipeIngredient = edtDesc.text.toString()
                recipeInstruction = edtDesc.text.toString()
                recipeImagePath = edtDesc.text.toString()

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

                    edtTitle.setText("")
                    edtDesc.setText("")

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