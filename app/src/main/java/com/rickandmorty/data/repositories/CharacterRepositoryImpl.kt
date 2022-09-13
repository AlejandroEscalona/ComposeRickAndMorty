package com.rickandmorty.data.repositories

import com.rickandmorty.data.Result
import com.rickandmorty.data.source.remote.RickAndMorty
import com.rickandmorty.data.source.remote.dto.toCharacter
import com.rickandmorty.data.source.remote.dto.toListCharacters
import com.rickandmorty.domain.model.Character
import com.rickandmorty.domain.model.Characters
import com.rickandmorty.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMorty
) : CharacterRepository {

    override fun getCharacters(page: Int): Flow<Result<List<Characters>>> = flow{
        emit(Result.Loading())
        try {
            val response = api.getCharacters(page).toListCharacters()
            emit(Result.Success(response))
        }catch(e: HttpException){
            emit(Result.Error(
                message = "Oops, something went wrong",
                data = null
            ))
        }catch(e: IOException){
            emit(Result.Error(
                message = "Server is not working",
                data = null
            ))
        }
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        val response = try {
            api.getCharacter(id)
        } catch (e: Exception) {
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toCharacter())
    }
}
