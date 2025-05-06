package com.example.rickandmorty.model

import android.content.Context
import com.example.rickandmorty.model.api.CharacterApi
import com.example.rickandmorty.model.api.RetrofitInstance
import com.example.rickandmorty.model.data.FavoritesSharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providerCharacterApi(): CharacterApi = RetrofitInstance.api

    @Provides
    @Singleton
    fun providerSharedPreference(@ApplicationContext context: Context): FavoritesSharedPreference {
        return FavoritesSharedPreference(context)
    }
}