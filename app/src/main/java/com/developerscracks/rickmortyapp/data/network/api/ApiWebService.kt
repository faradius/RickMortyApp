package com.developerscracks.rickmortyapp.data.network.api


import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.model.CharacterListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiWebService {

    @GET("character")
    suspend fun getCharacters(): CharacterListDTO

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDTO

    @GET("character/")
    suspend fun getCharacterByName(@Query("name") name:String): CharacterListDTO
}