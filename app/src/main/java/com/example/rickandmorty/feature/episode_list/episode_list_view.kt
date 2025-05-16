@file:OptIn(ExperimentalMaterial3Api::class)


package com.example.rickandmorty.feature.episode_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun EpisodeListView(navController: NavController) {
    val viewModel: EpisodeListViewModel = hiltViewModel()

    val episodes = viewModel.episodes

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Episodes", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("characterListView")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }) { innerPad ->
        LazyColumn(
            modifier = Modifier.padding(innerPad)
                .padding(horizontal = 15.dp)
        ) {
            itemsIndexed(episodes) { index, ep ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                        .clickable{},
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ep.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center
                            )
                            )
                    }
                }
            }
        }
    }
}