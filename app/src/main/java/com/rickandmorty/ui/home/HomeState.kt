package com.rickandmorty.ui.home

import com.rickandmorty.domain.model.Characters

data class HomeState(
    val characters: List<Characters> = emptyList(),
    val showPreviews : Boolean = false,
    val showNext : Boolean = false,
    val isLoading : Boolean = false
)
