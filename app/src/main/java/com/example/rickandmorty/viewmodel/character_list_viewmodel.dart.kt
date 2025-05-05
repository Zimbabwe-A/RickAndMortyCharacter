package com.example.rickandmorty.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.api.RetrofitInstance
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    var characters = mutableStateOf<List<Character>>(emptyList())

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCharacters()
                characters.value = response.results
            } catch (e: Exception) {
                Log.e("CharacterVM", "Error: ${e.message}")
            }
        }
    }


}
