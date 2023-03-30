package com.developerscracks.rickmortyapp.data.network.datasource

import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.network.api.ApiWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterDataSourceNetworkImpl @Inject constructor(private val api: ApiWebService): CharacterDataSourceNetwork{
    override suspend fun getCharacters(): Response<List<CharacterDTO>> {
        return withContext(Dispatchers.IO){
            try {
                val characterList = api.getCharacters()
                Response.Success(characterList.results)
            }catch (e:Exception){
                e.printStackTrace()
                Response.Error(e)
            }
        }
    }
}