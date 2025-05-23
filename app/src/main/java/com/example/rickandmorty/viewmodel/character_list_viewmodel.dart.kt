package com.example.rickandmorty.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.api.CharacterApi
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.data.FavoritesSharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val sharedPreference: FavoritesSharedPreference,
    private val characterApi: CharacterApi
) : ViewModel() {

    var characters by mutableStateOf<List<Character>>(emptyList())
        private set

    var favorites by mutableStateOf(setOf<String>())
        private set

    var showOnlyFavorites by mutableStateOf(false)
        private set

    init {
        fetchCharacters()
        observeFavorites()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = characterApi.getCharacters()
                characters = response.results
            } catch (e: Exception) {
                Log.e("CharacterVM", "Error: ${e.message}")
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            sharedPreference.favoriteIds.collectLatest {
                favorites = it
            }
        }
    }

    fun toggleFavorite(id: Int) {
        sharedPreference.toggleFavorite(id)
    }

    fun toggleFavoritesFilter() {
        showOnlyFavorites = !showOnlyFavorites
    }

    fun getFilteredCharacters(): List<Character> {
        return if (showOnlyFavorites) {
            characters.filter { favorites.contains(it.id.toString()) }
        } else {
            characters
        }
    }
}
