package com.example.grupodos_dam.webservice

import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.utils.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun getChallenges(): MutableList<Challenge>
}