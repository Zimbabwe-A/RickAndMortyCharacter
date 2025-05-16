package com.example.rickandmorty.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.feature.character_detail.CharacterDetailView
import com.example.rickandmorty.feature.character_list.CharacterListView
import com.example.rickandmorty.feature.episode_list.EpisodeListView

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "episodeListView") {
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
        composable("episodeListView") {
            EpisodeListView(navController)
        }
    }
}
