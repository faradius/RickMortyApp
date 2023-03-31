package com.developerscracks.rickmortyapp.domain.repository

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import com.developerscracks.rickmortyapp.data.model.CharacterDTO

interface CharacterRepository {
    suspend fun getCharacters(): Response<List<CharacterDTO>>

    suspend fun getCharacterById(id: Int):Response<CharacterDTO>

    suspend fun insertCharacterToFavorite(character: CharacterEntity)

    suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>>
}