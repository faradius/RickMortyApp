package com.developerscracks.rickmortyapp.ui.characterfavorites.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.domain.model.toCharacter
import com.developerscracks.rickmortyapp.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.developerscracks.rickmortyapp.domain.model.Character

@HiltViewModel
class CharacterListFavoritesViewModel @Inject constructor(private val repository: CharacterRepository): ViewModel() {

    private val _characterFavorites: MutableLiveData<List<Character>> = MutableLiveData()
    val characterFavorites: LiveData<List<Character>> = _characterFavorites

    fun getCharactersFavorites(){
        viewModelScope.launch {
            val result = repository.getAllCharactersFavorites()
            when(result){
                is Response.Success -> {
                    _characterFavorites.value = result.data.map {
                        it.toCharacter()
                    }
                }
                is Response.Error -> {
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }
}