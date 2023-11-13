package com.example.grupodos_dam.webservice

import ApiService

class ApiUtils {
    companion object{
        fun getApiService():ApiService{
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }

        suspend fun getRandomPokemon(): Pokemon? {
            val apiService = getApiService()
            try {
                val response = apiService.getPokemonList()

                if (response.pokemonList != null && !response.pokemonList.isEmpty()) {
                    return response.pokemonList.random()
                }
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()

                println("Error randomPokemon")
                println(e.message)
            }
            return null
        }
    }
}