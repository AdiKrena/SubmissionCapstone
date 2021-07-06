package com.adikrena.submissioncapstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adikrena.submissioncapstone.core.domain.usecase.RecipeUseCase

class HomeViewModel(recipeUseCase: RecipeUseCase) : ViewModel() {
    val recipes = recipeUseCase.getRandomRecipes().asLiveData()
}