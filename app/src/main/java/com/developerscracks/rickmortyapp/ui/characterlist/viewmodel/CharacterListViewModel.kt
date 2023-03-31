package com.developerscracks.rickmortyapp.ui.characterlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.rickmortyapp.core.Response
import com.developerscracks.rickmortyapp.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.developerscracks.rickmortyapp.domain.model.Character
import com.developerscracks.rickmortyapp.domain.model.toCharacter
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {

    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    val character: LiveData<List<Character>> = _characters

    init {
        getCharacters()
    }

    fun getCharacters(){
        viewModelScope.launch {
            val result = repository.getCharacters()
            when(result){
                is Response.Success->{
                    _characters.value = result.data.map {
                        it.toCharacter()
                    }
                }
                is Response.Error->{
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }

    fun getCharactersByName(name: String){
        viewModelScope.launch{
            val result = repository.getCharactersByName(name)
            when(result){
                is Response.Success->{
                    _characters.value = result.data.map {
                        it.toCharacter()
                    }
                }
                is Response.Error->{
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }
}