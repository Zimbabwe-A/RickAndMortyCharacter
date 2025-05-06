package com.example.rickandmorty.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.api.CharacterApi
import com.example.rickandmorty.model.api.RetrofitInstance
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.data.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterApi: CharacterApi
) : ViewModel() {
    var character by mutableStateOf<Character?>(null)
        private set

    var episodes by mutableStateOf<List<Episode>>(emptyList())
        private set

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val result = characterApi.getCharacterById(id)
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
