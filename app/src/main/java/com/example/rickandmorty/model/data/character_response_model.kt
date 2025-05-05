package com.example.rickandmorty.model.data


data class CharacterResponse(
    val results: List<Character>
)


data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val episode: List<String>
)
