package com.rickandmorty.data.source.remote.dto

import com.rickandmorty.domain.model.Character
import com.rickandmorty.domain.model.Characters

data class CharactersDto(
    val info: Info,
    val results: List<Result>
)

fun CharactersDto.toListCharacters() : List<Characters> {
    val resultEntries = results.mapIndexed { _, result ->
        Characters(
            id = result.id,
            name = result.name,
            image = result.image,
            specie = result.species,
            status = result.status
        )
    }
    return resultEntries
}