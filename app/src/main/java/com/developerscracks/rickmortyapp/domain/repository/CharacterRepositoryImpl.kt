package com.developerscracks.rickmortyapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.datasource.CharacterDataSourceLocal
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.network.api.ApiWebService
import com.developerscracks.rickmortyapp.data.network.datasource.CharacterDataSourceNetwork
import com.developerscracks.rickmortyapp.data.network.datasource.CharacterDataSourceNetworkImpl
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val networkDataSource: CharacterDataSourceNetwork,
    private val localDataSource: CharacterDataSourceLocal,
    private val api : ApiWebService
): CharacterRepository {
    override suspend fun getCharacters(): Response<List<CharacterDTO>> {
        return networkDataSource.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterDTO> {
        return networkDataSource.getCharacterById(id)
    }

    override suspend fun getCharactersByName(name: String): Response<List<CharacterDTO>> {
        return networkDataSource.getCharactersByName(name)
    }

    override suspend fun getCharacterFavoriteById(id: Int): Response<CharacterEntity> {
        return localDataSource.getCharacterFavoriteById(id)
    }

    override suspend fun insertCharacterToFavorite(character: CharacterEntity) {
        localDataSource.insertCharacterToFavorite(character)
    }

    override suspend fun deleteCharacterToFavorite(id: Int) {
        localDataSource.deleteCharacterToFavorite(id)
    }


    override suspend fun getAllCharactersFavorites(): Response<List<CharacterEntity>> {
        return localDataSource.getAllCharactersFavorites()
    }

    override fun getCharactersPage(): Flow<PagingData<CharacterDTO>> =
        Pager(
            config = PagingConfig(
                pageSize = 42,
                initialLoadSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { CharacterDataSourceNetworkImpl(api) }
        ).flow
}