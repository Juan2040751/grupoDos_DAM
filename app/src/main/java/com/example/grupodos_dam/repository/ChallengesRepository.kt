package com.example.grupodos_dam.repository

import android.content.Context
import com.example.grupodos_dam.data.ChallengesDB
import com.example.grupodos_dam.data.ChallengesDao
import com.example.grupodos_dam.model.Challenge
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
}