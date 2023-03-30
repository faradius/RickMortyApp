package com.developerscracks.rickmortyapp.data.network.api


import com.developerscracks.rickmortyapp.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiWebService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}