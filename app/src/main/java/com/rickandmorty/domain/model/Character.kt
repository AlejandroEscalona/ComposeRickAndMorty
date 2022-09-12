package com.rickandmorty.domain.model

import android.webkit.WebStorage
import com.rickandmorty.data.source.remote.dto.Location

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val specie: String,
    val gender: String,
    val origin: WebStorage.Origin,
    val location: Location,
    val image: String
)
