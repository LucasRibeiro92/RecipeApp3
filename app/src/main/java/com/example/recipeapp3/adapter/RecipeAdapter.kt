package com.example.recipeapp3.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
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

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.cardItem.setOnClickListener {
                val item = differ.currentList[adapterPosition]
                Log.d("Receita a ser instanciada no update fragment","$item")
                val bundle = Bundle()
                bundle.putSerializable("recipe", item)
                val fragment = UpdateRecipeFragment.newInstance()
                //fragment.setOnRecipeUpdatedListener(context as UpdateRecipeFragment.OnRecipeUpdatedListener)
                fragment.arguments = bundle
                fragment.show(
                    (binding.root.context as AppCompatActivity).supportFragmentManager,
                    UpdateRecipeFragment::class.java.simpleName
                )
            }
            /*
            binding.ckbIsFavoriteRecipe.setOnClickListener {
                val item = differ.currentList[adapterPosition]
                binding.apply {
                    ckbIsFavoriteRecipe.setImageDrawable("")
            }
            */
        }


        fun bind(item: RecipeEntity) {
            binding.apply {
                tvTitle.text = item.recipeTitle
                tvCuisine.text = item.recipeCuisine
                tvTimeToCook.text = item.recipeTime
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<RecipeEntity>() {
        override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            Log.d("TESTEEE", "fez as comparações dos ids")
            val testeBoolean = oldItem.recipeId == newItem.recipeId
            Log.d("TESTEEE", "$testeBoolean")
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean {
            Log.d("TESTEEE", "fez as comparações dos contens")
            val testeBoolean = oldItem == newItem
            Log.d("TESTEEE", "$testeBoolean")
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}