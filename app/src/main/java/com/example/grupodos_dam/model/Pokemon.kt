package com.example.grupodos_dam.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)
data class Pokemon(
    @SerializedName("id")
    val id:Int,

    @SerializedName("img")
    val img:String
)
