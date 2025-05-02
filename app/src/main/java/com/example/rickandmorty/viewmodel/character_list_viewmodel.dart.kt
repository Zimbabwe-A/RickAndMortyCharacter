package com.example.rickandmorty.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.RetrofitInstance
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    // Используем state для хранения данных
    var characters = mutableStateOf<List<Character>>(emptyList())

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCharacters()
                // Обновляем состояние после получения данных
                characters.value = response.results
                Log.d("CharacterVM", "Loaded characters: ${characters.value.size}")
            } catch (e: Exception) {
                Log.e("CharacterVM", "Error: ${e.message}")
            }
        }
    }
}
