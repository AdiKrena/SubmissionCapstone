package com.adikrena.submissioncapstone.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String?,
    val summary: String,
    val instructions: String,
    val isFavorite: Boolean
) : Parcelable