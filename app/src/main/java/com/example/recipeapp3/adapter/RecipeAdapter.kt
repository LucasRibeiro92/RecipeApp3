package com.example.recipeapp3.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp3.databinding.ItemRecipeBinding
import com.example.recipeapp3.db.RecipeEntity
import com.example.recipeapp3.ui.UpdateRecipeFragment

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private lateinit var binding: ItemRecipeBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRecipeBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.updateButton.setOnClickListener {
                val item = differ.currentList[adapterPosition]
                val bundle = Bundle()
                bundle.putSerializable("recipe", item)
                val fragment = UpdateRecipeFragment.newInstance()
                fragment.arguments = bundle
                fragment.show(
                    (binding.root.context as AppCompatActivity).supportFragmentManager,
                    UpdateRecipeFragment::class.java.simpleName
                )
            }
        }

        fun bind(item: RecipeEntity) {
            binding.apply {
                tvTitle.text = item.recipeTitle
                tvIngredient.text = item.recipeIngredient
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<RecipeEntity>() {
        override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}