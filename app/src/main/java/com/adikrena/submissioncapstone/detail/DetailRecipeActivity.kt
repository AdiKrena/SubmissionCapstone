package com.adikrena.submissioncapstone.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.adikrena.submissioncapstone.R
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import com.adikrena.submissioncapstone.databinding.ActivityDetailRecipeBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailRecipeActivity : AppCompatActivity() {

    private val detailRecipeViewModel: DetailRecipeViewModel by viewModel()
    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailRecipe = intent.getParcelableExtra<Recipe>(EXTRA_DATA)
        showDetailRecipe(detailRecipe)
    }

    private fun showDetailRecipe(detailRecipe: Recipe?) {
        detailRecipe?.let {
            supportActionBar?.title = it.title
            Glide.with(this@DetailRecipeActivity)
                .load(it.image)
                .into(binding.ivDetailImage)
            binding.content.tvDetailDescription.text = getFromHtml(it.summary)
            binding.content.tvReadyInMinutes.text =
                getString(R.string.ready_in_minutes, it.readyInMinutes)
            binding.content.tvServing.text = getString(R.string.servings, it.servings)
            binding.content.tvInstruction.text = getFromHtml(it.instructions)

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRecipeViewModel.setFavoriteRecipe(detailRecipe, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_black))
        }
    }

    private fun getFromHtml(text: String): Spanned =
        HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}