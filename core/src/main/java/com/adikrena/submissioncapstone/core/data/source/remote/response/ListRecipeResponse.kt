package com.adikrena.submissioncapstone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRecipeResponse(
    @SerializedName("recipes")
    val recipes: List<RecipeResponse>
)
