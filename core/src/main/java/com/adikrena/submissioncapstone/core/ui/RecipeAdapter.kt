package com.adikrena.submissioncapstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adikrena.submissioncapstone.core.R
import com.adikrena.submissioncapstone.core.databinding.ItemListRecipeBinding
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import com.bumptech.glide.Glide

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ListViewHolder>() {
    private var listData = ArrayList<Recipe>()
    var onItemClick: ((Recipe) -> Unit)? = null

    fun setData(newListData: List<Recipe>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_recipe, parent, false))

    override fun onBindViewHolder(holder: RecipeAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRecipeBinding.bind(itemView)
        fun bind(data: Recipe) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(binding.ivItemImage)
                tvItemTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}