package com.developerscracks.rickmortyapp.data.model

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDTO>
)