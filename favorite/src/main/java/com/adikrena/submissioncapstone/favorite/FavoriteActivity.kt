package com.adikrena.submissioncapstone.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adikrena.submissioncapstone.core.ui.RecipeAdapter
import com.adikrena.submissioncapstone.detail.DetailRecipeActivity
import com.adikrena.submissioncapstone.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite Recipe"
        getRecipeData()
    }

    private fun getRecipeData() {
        val adapter = RecipeAdapter()
        adapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailRecipeActivity::class.java)
            intent.putExtra(DetailRecipeActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteRecipe.observe(this, { recipes ->
            adapter.setData(recipes)
        })

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}