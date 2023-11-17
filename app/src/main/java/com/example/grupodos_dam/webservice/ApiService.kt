package com.example.grupodos_dam.webservice

import com.example.grupodos_dam.model.Pokemon
import com.example.grupodos_dam.model.PokemonListResponse
import retrofit2.http.GET
import com.example.grupodos_dam.utils.Constants.END_POINT

interface ApiService {
    @GET(END_POINT)
    suspend fun getPokemons(): PokemonListResponse
}
