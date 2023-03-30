package com.developerscracks.rickmortyapp.data.network.datasource

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.model.CharacterDTO

interface CharacterDataSourceNetwork {
    suspend fun getCharacters():Response<List<CharacterDTO>>
    suspend fun getCharacterById(id: Int):Response<CharacterDTO>
}