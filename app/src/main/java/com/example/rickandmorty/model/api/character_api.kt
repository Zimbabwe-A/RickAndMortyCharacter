package com.example.rickandmorty.model.api

import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.data.CharacterResponse
import com.example.rickandmorty.model.data.Episode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET
    suspend fun getEpisodeByUrl(@Url url: String): Episode
}

