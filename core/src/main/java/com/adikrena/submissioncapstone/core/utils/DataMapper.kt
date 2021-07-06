package com.adikrena.submissioncapstone.core.utils

import com.adikrena.submissioncapstone.core.data.source.local.entity.RecipeEntity
import com.adikrena.submissioncapstone.core.data.source.remote.response.RecipeResponse
import com.adikrena.submissioncapstone.core.domain.model.Recipe

object DataMapper {
    fun mapResponsesToEntities(input: List<RecipeResponse>): List<RecipeEntity> {
        val recipeList = ArrayList<RecipeEntity>()
        input.map {
            val recipe = RecipeEntity(
                id = it.id,
                title = it.title,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                image = it.image,
                summary = it.summary,
                instructions = it.instructions,
                isFavorite = false
            )
            recipeList.add(recipe)
        }
        return recipeList
    }

    fun mapEntitiesToDomain(input: List<RecipeEntity>): List<Recipe> =
        input.map {
            Recipe(
                id = it.id,
                title = it.title,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                image = it.image,
                summary = it.summary,
                instructions = it.instructions,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Recipe): RecipeEntity = RecipeEntity(
        id = input.id,
        title = input.title,
        readyInMinutes = input.readyInMinutes,
        servings = input.servings,
        image = input.image,
        summary = input.summary,
        instructions = input.instructions,
        isFavorite = input.isFavorite
    )
}