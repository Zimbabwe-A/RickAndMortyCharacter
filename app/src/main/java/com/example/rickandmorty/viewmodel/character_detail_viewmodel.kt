package com.example.rickandmorty.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.api.RetrofitInstance
import com.example.rickandmorty.model.data.Episode
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    var character by mutableStateOf<Character?>(null)
        private set

    var episodes by mutableStateOf<List<Episode>>(emptyList())
        private set

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getCharacterById(id)
                character = result

                episodes = result.episode.map { url ->
                    RetrofitInstance.api.getEpisodeByUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
