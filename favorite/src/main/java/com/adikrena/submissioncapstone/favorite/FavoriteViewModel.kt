package com.adikrena.submissioncapstone.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adikrena.submissioncapstone.core.domain.usecase.RecipeUseCase

class FavoriteViewModel(recipeUseCase: RecipeUseCase) : ViewModel() {
    val favoriteRecipe = recipeUseCase.getFavoriteRecipes().asLiveData()
}