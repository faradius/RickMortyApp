package com.developerscracks.rickmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developerscracks.rickmortyapp.data.local.dao.CharacterDao
import com.developerscracks.rickmortyapp.data.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao():CharacterDao
}