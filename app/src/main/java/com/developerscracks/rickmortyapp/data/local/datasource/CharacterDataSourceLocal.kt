package com.developerscracks.rickmortyapp.data.local.datasource

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity

interface CharacterDataSourceLocal {
    suspend fun insertCharacterToFavorite(character: CharacterEntity)
    suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>>

    suspend fun getCharacterFavoriteById(id: Int): Response<CharacterEntity>
    suspend fun deleteCharacterToFavorite(id: Int)
}