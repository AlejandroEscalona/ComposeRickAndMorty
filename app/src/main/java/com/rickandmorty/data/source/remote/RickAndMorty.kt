package com.rickandmorty.data.source.remote

import com.rickandmorty.data.source.remote.dto.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMorty{

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharactersDto
}
