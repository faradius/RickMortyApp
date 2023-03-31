package com.developerscracks.rickmortyapp.domain.repository

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.datasource.CharacterDataSourceLocal
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.network.datasource.CharacterDataSourceNetwork

import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: CharacterDataSourceNetwork,
    private val localDataSource: CharacterDataSourceLocal
): CharacterRepository {
    override suspend fun getCharacters(): Response<List<CharacterDTO>> {
        return networkDataSource.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterDTO> {
        return networkDataSource.getCharacterById(id)
    }

    override suspend fun insertCharacterToFavorite(character: CharacterEntity) {
        localDataSource.insertCharacterToFavorite(character)
    }

    override suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>> {
        return localDataSource.getAllCharactersFavorites()
    }
}