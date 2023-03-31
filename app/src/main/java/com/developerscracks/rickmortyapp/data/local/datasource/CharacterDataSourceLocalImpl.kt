package com.developerscracks.rickmortyapp.data.local.datasource

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.dao.CharacterDao
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import javax.inject.Inject

class CharacterDataSourceLocalImpl @Inject constructor(private val characterDao: CharacterDao): CharacterDataSourceLocal {
    override suspend fun insertCharacterToFavorite(character: CharacterEntity) {
        characterDao.insertCharacterToFavorite(character)
    }

    override suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>> {
        return Response.Success(characterDao.getAllCharactersFavorites())
    }

    override suspend fun getCharacterFavoriteById(id: Int): Response<CharacterEntity> {
        return Response.Success(characterDao.getCharacterFavoriteById(id))
    }

    override suspend fun deleteCharacterToFavorite(id: Int) {
        characterDao.deleteCharacterToFavorite(id)
    }

}