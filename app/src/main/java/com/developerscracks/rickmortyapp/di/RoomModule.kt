package com.developerscracks.rickmortyapp.di

import android.content.Context
import androidx.room.Room
import com.developerscracks.rickmortyapp.data.local.AppDatabase
import com.developerscracks.rickmortyapp.data.local.dao.CharacterDao
import com.developerscracks.rickmortyapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(db: AppDatabase): CharacterDao{
        return db.characterDao()
    }
}