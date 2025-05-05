package com.example.rickandmorty.model.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesSharedPreference(context: Context) {

    private val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val FAVORITES_KEY = "favorite_ids"

    // Загружаем избранные элементы один раз
    private val _favoriteIds = MutableStateFlow(loadFavorites())
    val favoriteIds: Flow<Set<String>> = _favoriteIds.asStateFlow()

    // Загружаем избранные элементы из SharedPreferences
    private fun loadFavorites(): Set<String> {
        return prefs.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }

    // Меняем состояние избранного
    fun toggleFavorite(id: Int) {
        val current = _favoriteIds.value.toMutableSet() // Используем уже загруженные данные
        val idStr = id.toString()
        if (current.contains(idStr)) {
            current.remove(idStr)
        } else {
            current.add(idStr)
        }

        // Обновляем SharedPreferences и внутреннее состояние
        prefs.edit().putStringSet(FAVORITES_KEY, current).apply()
        _favoriteIds.value = current
    }
}
