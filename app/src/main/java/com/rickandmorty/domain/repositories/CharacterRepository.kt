package com.rickandmorty.domain.repositories

import com.rickandmorty.data.Result
import com.rickandmorty.domain.model.Character
import com.rickandmorty.domain.model.Characters
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(page: Int): Flow<Result<List<Characters>>>

    suspend fun getCharacter(id: Int): Result<Character>
}
