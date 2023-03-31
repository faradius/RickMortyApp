package com.developerscracks.rickmortyapp.domain.model.characterdetail

import com.developerscracks.rickmortyapp.data.model.CharacterDTO
import com.developerscracks.rickmortyapp.data.model.Location
import com.developerscracks.rickmortyapp.data.model.Origin

data class CharacterDetail(
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String
)

fun CharacterDTO.toCharacterDetail(): CharacterDetail {
    return CharacterDetail(
        id = this.id,
        name = this.name,
        image = this.image,
        species = this.species,
        status = this.status,
        location = this.location,
        origin = this.origin
    )
}