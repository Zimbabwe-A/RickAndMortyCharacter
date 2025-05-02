package com.example.rickandmorty.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.RetrofitInstance
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    var character by mutableStateOf<Character?>(null)
        private set

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            try {
                character = RetrofitInstance.api.getCharacterById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
