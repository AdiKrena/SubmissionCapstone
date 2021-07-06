package com.adikrena.submissioncapstone.detail

import androidx.lifecycle.ViewModel
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import com.adikrena.submissioncapstone.core.domain.usecase.RecipeUseCase

class DetailRecipeViewModel(private val recipeUseCase: RecipeUseCase) : ViewModel() {
    fun setFavoriteRecipe(recipe: Recipe, newState: Boolean) =
        recipeUseCase.setFavoriteRecipe(recipe, newState)
}