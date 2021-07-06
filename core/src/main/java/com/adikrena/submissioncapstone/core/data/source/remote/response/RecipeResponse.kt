package com.adikrena.submissioncapstone.core.data.source.remote.response

data class RecipeResponse(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String?,
    val summary: String,
    val instructions: String
)