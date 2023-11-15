package com.example.grupodos_dam.model

import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("id")
    val id:Int,

    //@SerializedName("name")
    //val name: String,

    @SerializedName("image")
    val img:String
)

/*
data class PokemonResponse(val pokemonList: List<Pokemon>)
data class Pokemon( val id: Int, val num: String, val name: String, val img: String, val type: List<String>, val height: String, val weight: String, val candy: String, val candy_count: Int, val egg: String, val spawn_chance: Double, val avg_spawns: Int, val spawn_time: String, val multipliers: List<Double>, val weaknesses: List<String>, val next_evolution: List<Evolution> )
data class Evolution( val num: String, val name: String )
*/