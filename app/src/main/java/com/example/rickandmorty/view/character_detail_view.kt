package com.example.rickandmorty.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.viewmodel.CharacterDetailViewModel

@Composable
fun CharacterDetailView(navController: NavController, characterId: Int) {
    val viewModel: CharacterDetailViewModel = viewModel()
    val character = viewModel.character
    val episode = viewModel.episodes

    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            character?.let {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),

                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = it.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(160.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                it.name,
                                style = TextStyle(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    //            Criterii
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(it.status, style = TextStyle(fontSize = 18.sp))
                        Column {
                            Text("Species", style = TextStyle(fontSize = 18.sp))
                            Spacer(Modifier.height(8.dp))
                            Text(it.species, style = TextStyle(fontSize = 18.sp))
                        }
                        Column {
                            Text("Gender", style = TextStyle(fontSize = 18.sp))
                            Spacer(Modifier.height(8.dp))
                            Text(it.gender,style = TextStyle(fontSize = 18.sp))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    //            Diveder
                    HorizontalDivider(thickness = 5.dp, color = Color.LightGray)
//            Episodes
                    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
                        Text(
                            text = "Episodes",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W500,
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        if (episode.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            LazyColumn {
                                items(episode.size) { it ->
                                    val epis = episode[it]
                                    Text(
                                        text = "• Episode ${epis.id}. ${epis.name}",
                                        fontSize = 22.sp,
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
