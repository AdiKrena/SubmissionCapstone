package com.adikrena.submissioncapstone.core.data.source.local

import com.adikrena.submissioncapstone.core.data.source.local.entity.RecipeEntity
import com.adikrena.submissioncapstone.core.data.source.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val recipeDao: RecipeDao) {
    fun getRandomRecipes(): Flow<List<RecipeEntity>> = recipeDao.getRandomRecipes()

    fun getFavoriteRecipes(): Flow<List<RecipeEntity>> = recipeDao.getFavoriteRecipes()

    suspend fun insertRecipes(recipeList: List<RecipeEntity>) = recipeDao.insertRecipes(recipeList)

    fun updateFavoriteRecipe(recipe: RecipeEntity, newState: Boolean) {
        recipe.isFavorite = newState
        recipeDao.updateFavoriteRecipe(recipe)
    }
}