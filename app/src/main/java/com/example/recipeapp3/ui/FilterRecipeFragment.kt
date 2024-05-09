package com.example.recipeapp3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp3.R
import com.example.recipeapp3.databinding.FragmentFilterRecipeBinding
import com.example.recipeapp3.databinding.FragmentUpdateRecipeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class FilterRecipeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterRecipeBinding? = null
    private val binding get() = _binding
    private var filterListener: FilterListener? = null

    companion object {
        fun newInstance() = FilterRecipeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterRecipeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        binding?.apply {

            imgClose.setOnClickListener { dismiss() }
            btnFilter.setOnClickListener {

                val recipeCuisineToFilter = spnRecipeCuisineToFilter.selectedItem.toString()
                val recipeCategoryToFilter = spnRecipeCategoryToFilter.selectedItem.toString()

                filterListener?.onFilterApplied(recipeCuisineToFilter, recipeCategoryToFilter)

                spnRecipeCuisineToFilter.setSelection(0)
                spnRecipeCategoryToFilter.setSelection(0)

                dismiss()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface FilterListener {
        fun onFilterApplied(cuisine: String, category: String)
    }
}

