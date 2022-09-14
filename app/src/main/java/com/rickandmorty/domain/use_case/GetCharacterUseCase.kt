package com.rickandmorty.domain.use_case

import com.rickandmorty.data.Result
import com.rickandmorty.domain.model.Character
import com.rickandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase  @Inject constructor(
    private val respository: CharacterRepository
){
    suspend operator fun invoke(id: Int): Result<Character> {
        return respository.getCharacter(id)
    }
}