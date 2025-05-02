@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmorty.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CharacterListView(navController: NavController) {
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
                }
            )
        }
    ) { innerPad ->
        LazyColumn(
            modifier = Modifier.padding(innerPad)
        ) {
            items(10) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 10.dp)
                        .height(140.dp)
                        .fillMaxWidth()
                        .clickable{
                            navController.navigate("characterDetailView")
                        },
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                       verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp)
                                .background(
                                    Color.Red,
                                    shape = CircleShape
                                )
                        ) {}
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Rick Sanchez",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.W500,
                                )
                            )
                            Text(
                                text = "Alive",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}