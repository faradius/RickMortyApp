package com.developerscracks.rickmortyapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerscracks.rickmortyapp.data.model.Location
import com.developerscracks.rickmortyapp.data.model.Origin

@Entity(tableName = "Characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "imagen")
    val image: String,
    @ColumnInfo(name = "locacion")
    val location: String,
    @ColumnInfo(name = "nombre")
    val name: String,
    @ColumnInfo(name = "origen")
    val origin: String,
    @ColumnInfo(name = "especie")
    val species: String,
    @ColumnInfo(name = "estado")
    val status: String
)