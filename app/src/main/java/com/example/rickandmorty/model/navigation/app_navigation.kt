package com.example.rickandmorty.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.rickandmorty.feature.character_detail.CharacterDetailView
import com.example.rickandmorty.feature.character_list.CharacterListView

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "characterListView") {
        composable("characterListView") {
            CharacterListView(navController)
        }
        composable(
            route = "characterDetailView/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            CharacterDetailView(navController, characterId = id)
        }
    }
}
