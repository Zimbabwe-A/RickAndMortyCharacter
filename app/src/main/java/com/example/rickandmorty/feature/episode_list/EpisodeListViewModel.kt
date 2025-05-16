package com.example.rickandmorty.feature.episode_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.api.CharacterApi
import com.example.rickandmorty.model.data.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val characterApi: CharacterApi,
) : ViewModel() {
    var episodes by mutableStateOf<List<Episode>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadEpisodes()
    }

    fun loadEpisodes() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = characterApi.getEpisodes()
                episodes = response.results
            } catch (e : Exception) {
                Log.e("Episode", "Error: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}