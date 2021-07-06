package com.adikrena.submissioncapstone.core.data.source.local.room

import androidx.room.*
import com.adikrena.submissioncapstone.core.data.source.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getRandomRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE isFavorite = 1")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Update
    fun updateFavoriteRecipe(recipe: RecipeEntity)
}