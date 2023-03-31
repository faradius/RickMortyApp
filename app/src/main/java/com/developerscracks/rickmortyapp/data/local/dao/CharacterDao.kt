package com.developerscracks.rickmortyapp.data.local.dao

import androidx.room.*
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM Characters")
    suspend fun getAllCharactersFavorites():List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterToFavorite(character: CharacterEntity)

    @Delete
    suspend fun deleteCharacterToFavorite(character: CharacterEntity)
}