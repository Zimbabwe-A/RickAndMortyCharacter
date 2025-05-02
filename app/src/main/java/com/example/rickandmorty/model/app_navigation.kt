package com.example.rickandmorty.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.view.CharacterDetailView
import com.example.rickandmorty.view.CharacterListView

@Composable
fun AppNavigation() {
    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "characterListView",
    ) {
        composable("characterListView") { CharacterListView(navController)}
        composable("characterDetailView") { CharacterDetailView(navController) }
    }

}