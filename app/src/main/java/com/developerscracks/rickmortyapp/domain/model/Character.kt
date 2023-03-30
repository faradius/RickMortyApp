package com.developerscracks.rickmortyapp.domain.model

import com.developerscracks.rickmortyapp.data.model.CharacterDTO

data class Character(
    val id: Int,
    val name: String,
    val image: String,
)

fun CharacterDTO.toCharacter(): Character{
    return Character(
        id = this.id,
        name = this.name,
        image = this.image
    )
}
