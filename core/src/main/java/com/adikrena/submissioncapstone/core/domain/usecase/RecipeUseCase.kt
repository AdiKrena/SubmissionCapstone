package com.adikrena.submissioncapstone.core.domain.usecase

import com.adikrena.submissioncapstone.core.data.Resource
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    fun getRandomRecipes(): Flow<Resource<List<Recipe>>>
    fun getFavoriteRecipes(): Flow<List<Recipe>>
    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)
}