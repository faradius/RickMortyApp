package com.developerscracks.rickmortyapp.ui.characterdetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity
import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.developerscracks.rickmortyapp.domain.model.characterdetail.CharacterDetail
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characterDetail: MutableLiveData<CharacterDTO?> = MutableLiveData()
    val characterDetail: LiveData<CharacterDTO?> = _characterDetail

    fun getCharacterDetail(id:Int){
        viewModelScope.launch {
            val result = repository.getCharacterById(id)
            when(result){
                is Response.Success -> {
                    _characterDetail.value = result.data
                }

                is Response.Error -> {
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }

    fun saveCharacterFavorite(character: CharacterEntity){
        viewModelScope.launch {
            repository.insertCharacterToFavorite(character)
        }
    }
}