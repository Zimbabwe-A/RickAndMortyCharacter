package com.example.rickandmorty.view

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
fun CharacterDetailView(navController: NavController) {
    Scaffold { innerPad ->
        Column(
            modifier = Modifier
                .padding(innerPad)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
//            Icon BACK
            IconButton(
                onClick = {navController.popBackStack()}
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Arrow"
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //            Image Person
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .width(180.dp)
                            .background(
                                Color.Gray,
                                shape = CircleShape
                            ),
                    ) { }
                    Spacer(Modifier.height(20.dp))
                    //            Name Person
                    Text(
                        "Rick Sanchez",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W500,
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
                Text("Alive", style = TextStyle(fontSize = 18.sp))
                Column {
                    Text("Species", style = TextStyle(fontSize = 18.sp))
                    Spacer(Modifier.height(8.dp))
                    Text("Human", style = TextStyle(fontSize = 18.sp))
                }
                Column {
                    Text("Gender", style = TextStyle(fontSize = 18.sp))
                    Spacer(Modifier.height(8.dp))
                    Text("Male",style = TextStyle(fontSize = 18.sp))
                }
            }
            Spacer(Modifier.height(10.dp))
//            Diveder
            HorizontalDivider(thickness = 5.dp)
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
                LazyColumn {
                    items(5) { index ->
                        Row(
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text("• ", fontSize = 18.sp)
                            Text("episode", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}