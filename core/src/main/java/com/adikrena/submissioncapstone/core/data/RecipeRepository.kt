package com.adikrena.submissioncapstone.core.data

import com.adikrena.submissioncapstone.core.data.source.local.LocalDataSource
import com.adikrena.submissioncapstone.core.data.source.remote.RemoteDataSource
import com.adikrena.submissioncapstone.core.data.source.remote.network.ApiResponse
import com.adikrena.submissioncapstone.core.data.source.remote.response.RecipeResponse
import com.adikrena.submissioncapstone.core.domain.model.Recipe
import com.adikrena.submissioncapstone.core.domain.repository.IRecipeRepository
import com.adikrena.submissioncapstone.core.utils.AppExecutors
import com.adikrena.submissioncapstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {
    override fun getRandomRecipes(): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeResponse>>() {
            override fun loadFromDB(): Flow<List<Recipe>> =
                localDataSource.getRandomRecipes().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Recipe>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RecipeResponse>>> =
                remoteDataSource.getRandomRecipes()

            override suspend fun saveCallResult(data: List<RecipeResponse>) {
                val recipeList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertRecipes(recipeList)
            }
        }.asFlow()

    override fun getFavoriteRecipes(): Flow<List<Recipe>> =
        localDataSource.getFavoriteRecipes().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteRecipe(recipeEntity, state) }
    }
}