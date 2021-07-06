package com.adikrena.submissioncapstone.core.domain.repository

import com.adikrena.submissioncapstone.core.data.Resource
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    fun getRandomRecipes(): Flow<Resource<List<Recipe>>>

    fun getFavoriteRecipes(): Flow<List<Recipe>>

    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)
}