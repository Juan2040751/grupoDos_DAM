package com.example.grupodos_dam.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.grupodos_dam.data.ChallengesDB
import com.example.grupodos_dam.data.ChallengesDao
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.model.Pokemon
import com.example.grupodos_dam.webservice.ApiService
import com.example.grupodos_dam.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengesRepository (val context: Context){
    private var challengesDao: ChallengesDao = ChallengesDB.getDatabase(context).challengesDao()
    private var apiService: ApiService = ApiUtils.getApiService()
     suspend fun saveChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            challengesDao.saveChallenge(challenge)
        }
    }
    suspend fun getListChallenges():MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            challengesDao.getListChallenges()
        }
    }

    suspend fun deleteChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            challengesDao.deleteChallenge(challenge)
        }
    }

    suspend fun updateChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            challengesDao.updateChallenge(challenge)
        }
    }
    suspend fun getRandomChallenge(): MutableLiveData<Challenge> {
        val challenges = challengesDao.getListChallenges()

        val randomChallenge = if (challenges != null && challenges.isNotEmpty()) {
            challenges.random()
        } else {
            Challenge(0, "No hay retos aún")
        }

        return MutableLiveData(randomChallenge)
    }

    suspend fun getPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemons()
                response.pokemon
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}