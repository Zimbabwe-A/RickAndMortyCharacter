package com.example.rickandmorty.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.model.api.RetrofitInstance
import com.example.rickandmorty.model.data.FavoritesSharedPreference
import com.example.rickandmorty.viewmodel.CharacterListViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListView(
    navController: NavController,
) {

    val context = LocalContext.current
    val sharedPref = remember { FavoritesSharedPreference(context) }
    val api = remember { RetrofitInstance.api }

    val viewModel: CharacterListViewModel = viewModel(
        factory = CharacterListViewModel.provideFactory(sharedPref, api)
    )
    val characters = viewModel.getFilteredCharacters()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Character",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                        )
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.toggleFavoritesFilter()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (viewModel.showOnlyFavorites) Color.Red else Color.Gray
                        )
                    }
                }
            )
        }
    ) { innerPad ->
        if (characters.isEmpty()) {
            if (viewModel.showOnlyFavorites == true) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPad), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Добавьте в избранное персонажа",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            } else {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPad), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPad)
            ) {
                items(characters.size) { index ->
                    val character = characters[index]
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 10.dp)
                            .height(140.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("characterDetailView/${character.id}")
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = character.image,
                                contentDescription = "Image Person",
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(Modifier.width(10.dp))
                            Column(
                                modifier = Modifier.width(200.dp)
                            ) {
                                Text(
                                    text = character.name,
                                    style = TextStyle(
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.W500,
                                    )
                                )
                                Text(
                                    text = character.status,
                                    style = TextStyle(
                                        color = Color.Gray,
                                        fontSize = 18.sp
                                    )
                                )
                            }
                            Spacer(Modifier.weight(1f))
                            IconButton(onClick = {
                                viewModel.toggleFavorite(character.id)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Add to favorites",
                                    tint = if (viewModel.favorites.contains(character.id.toString())) Color.Red else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
