package com.example.rickandmorty.model.data

data class EpisodeResponse(
    var results: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String
)
