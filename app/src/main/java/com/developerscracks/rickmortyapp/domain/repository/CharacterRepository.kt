package com.developerscracks.rickmortyapp.domain.repository

import androidx.paging.PagingData
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Response<List<CharacterDTO>>

    suspend fun getCharacterById(id: Int):Response<CharacterDTO>

    suspend fun getCharactersByName(name: String): Response<List<CharacterDTO>>

    suspend fun getCharacterFavoriteById(id: Int): Response<CharacterEntity>

    suspend fun insertCharacterToFavorite(character: CharacterEntity)

    suspend fun deleteCharacterToFavorite(id: Int)

    suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>>

    fun getCharactersPage(): Flow<PagingData<CharacterDTO>>
}