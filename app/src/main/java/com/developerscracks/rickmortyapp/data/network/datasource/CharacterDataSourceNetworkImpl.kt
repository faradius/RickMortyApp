package com.developerscracks.rickmortyapp.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.network.api.ApiWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val FIRST_PAGE = 1
class CharacterDataSourceNetworkImpl @Inject constructor(
    private val api: ApiWebService
    ): PagingSource<Int, CharacterDTO>(),CharacterDataSourceNetwork {
    override suspend fun getCharacters(): Response<List<CharacterDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                val characterListResult = api.getCharacters()
                Response.Success(characterListResult.results)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Error(e)
            }
        }
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterDTO> {
        return withContext(Dispatchers.IO) {
            try {
                val characterResult = api.getCharacterById(id)
                Response.Success(characterResult)
            } catch (e: Exception){
                e.printStackTrace()
                Response.Error(e)
            }
        }

    }

    override suspend fun getCharactersByName(name: String): Response<List<CharacterDTO>> {
        return withContext(Dispatchers.IO){
            try {
                val characterListResult = api.getCharacterByName(name)
                Response.Success(characterListResult.results)
            }catch (e: Exception){
                e.printStackTrace()
                Response.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {
        val page = params.key ?: FIRST_PAGE
        val size = params.loadSize
        val previousPage = if (page == FIRST_PAGE) null else page - 1

        return try {
            val responseCharacters = api.getCharactersPage(page,size)
            val nextPage = if (responseCharacters.results.isEmpty()){
                null
            }else page + 1
            LoadResult.Page(
                responseCharacters.results,
                previousPage,
                nextPage
            )
        }catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}