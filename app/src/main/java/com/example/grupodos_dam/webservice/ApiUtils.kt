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
                if (!response.pokemon.isEmpty()) {
                    return response.pokemon.random()
                }
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
            }
            return null
        }
    }
}