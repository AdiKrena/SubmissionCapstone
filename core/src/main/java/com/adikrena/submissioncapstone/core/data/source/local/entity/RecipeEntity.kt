package com.adikrena.submissioncapstone.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "readyInMinutes")
    var readyInMinutes: Int,

    @ColumnInfo(name = "servings")
    var servings: Int,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "summary")
    var summary: String,

    @ColumnInfo(name = "instructions")
    var instructions: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)