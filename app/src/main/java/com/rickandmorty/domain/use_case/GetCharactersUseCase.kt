package com.rickandmorty.domain.use_case

import com.rickandmorty.data.Result
import com.rickandmorty.domain.model.Characters
import com.rickandmorty.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page : Int) : Flow<Result<List<Characters>>> {
        return repository.getCharacters(page)
    }
}