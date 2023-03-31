package com.developerscracks.rickmortyapp.di

import com.developerscracks.rickmortyapp.data.local.datasource.CharacterDataSourceLocal
import com.developerscracks.rickmortyapp.data.local.datasource.CharacterDataSourceLocalImpl
import com.developerscracks.rickmortyapp.data.network.datasource.CharacterDataSourceNetwork
import com.developerscracks.rickmortyapp.data.network.datasource.CharacterDataSourceNetworkImpl
import com.developerscracks.rickmortyapp.domain.repository.CharacterRepository
import com.developerscracks.rickmortyapp.domain.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CharacterModule {

    @Binds
    abstract fun bindCharacterDataSourceNetwork(impl: CharacterDataSourceNetworkImpl): CharacterDataSourceNetwork

    @Binds
    abstract fun bindCharacterDataSourceLocal(impl: CharacterDataSourceLocalImpl): CharacterDataSourceLocal

    @Binds
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl):CharacterRepository
}