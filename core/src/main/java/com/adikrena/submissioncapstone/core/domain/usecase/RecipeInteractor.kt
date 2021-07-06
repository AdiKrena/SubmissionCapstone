package com.adikrena.submissioncapstone.core.domain.usecase

import com.adikrena.submissioncapstone.core.domain.model.Recipe
import com.adikrena.submissioncapstone.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeInteractor(private val recipeRepository: IRecipeRepository) : RecipeUseCase {
    override fun getRandomRecipes() = recipeRepository.getRandomRecipes()
    override fun getFavoriteRecipes(): Flow<List<Recipe>> = recipeRepository.getFavoriteRecipes()
    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) =
        recipeRepository.setFavoriteRecipe(recipe, state)
}