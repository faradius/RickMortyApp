package com.developerscracks.rickmortyapp.data.local.dao

import androidx.room.*
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM Characters")
    suspend fun getAllCharactersFavorites():List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterToFavorite(character: CharacterEntity)

    @Query("SELECT * FROM Characters WHERE id = :id")
    suspend fun getCharacterFavoriteById(id: Int): CharacterEntity

    @Query("DELETE FROM Characters WHERE id = :id")
    suspend fun deleteCharacterToFavorite(id: Int)
}