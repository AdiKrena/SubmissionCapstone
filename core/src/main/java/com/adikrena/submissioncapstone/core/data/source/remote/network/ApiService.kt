package com.adikrena.submissioncapstone.core.data.source.remote.network

import com.adikrena.submissioncapstone.core.data.source.remote.response.ListRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @GET("recipes/random?apiKey=ce03009bc7574ff9b315dbdf4219214d&number=20")
    @Headers("Content-Type: application/json")
    suspend fun randomRecipes(): ListRecipeResponse
}