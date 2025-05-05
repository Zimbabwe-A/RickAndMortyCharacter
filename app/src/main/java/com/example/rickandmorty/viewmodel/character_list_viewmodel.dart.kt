package com.example.rickandmorty.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.api.RetrofitInstance
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.data.FavoritesSharedPreference
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val sharedPreference: FavoritesSharedPreference
) : ViewModel() {

    var characters by mutableStateOf<List<Character>>(emptyList())
        private set

    var favorites by mutableStateOf(setOf<String>())
        private set

    var showOnlyFavorites by mutableStateOf(false)

    init {
        fetchCharacters()
        observeFavorites()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCharacters()
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

    fun getFilteredCharacters(): List<Character> {
        return if (showOnlyFavorites) {
            characters.filter { favorites.contains(it.id.toString()) }
        } else {
            characters
        }
    }

    companion object {
        fun provideFactory(sharedPreference: FavoritesSharedPreference): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CharacterListViewModel(sharedPreference) as T
                }
            }
        }
    }
}
