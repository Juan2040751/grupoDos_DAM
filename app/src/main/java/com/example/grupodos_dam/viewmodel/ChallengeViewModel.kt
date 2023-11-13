package com.example.grupodos_dam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.repository.ChallengesRepository
import com.example.grupodos_dam.webservice.Pokemon
import com.example.grupodos_dam.webservice.ApiUtils
import kotlinx.coroutines.launch
import kotlin.random.Random

class ChallengesViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val challengesRepository = ChallengesRepository(context)

    private val _listChallenges = MutableLiveData<MutableList<Challenge>>()
    val listChallenges: LiveData<MutableList<Challenge>> get() = _listChallenges

    private var _randomChallenge = MutableLiveData<Challenge>()
    val randomChallenge: LiveData<Challenge> get() = _randomChallenge

    private var _randomPokemon = MutableLiveData<Pokemon>()
    val randomPokemon: LiveData<Pokemon> get() = _randomPokemon

    init {
        getRandomChallenge()
    }
    fun saveChallenge(challenge: Challenge){
        viewModelScope.launch {
            challengesRepository.saveChallenge(challenge)
        }
    }

    fun getListChallenges(){
        viewModelScope.launch {
            _listChallenges.value = challengesRepository.getListChallenges()
        }
    }
    fun deleteChallenge(challenge: Challenge){
        viewModelScope.launch {
            challengesRepository.deleteChallenge(challenge)
        }
    }

    fun updateChallenge(challenge: Challenge){
        viewModelScope.launch {
            challengesRepository.updateChallenge(challenge)
        }
    }
    fun getRandomChallenge() {
        viewModelScope.launch {
            _randomChallenge = challengesRepository.getRandomChallenge()
        }
    }

    fun getRandomPokemon() {
        viewModelScope.launch {
            try {
                _randomPokemon.value = ApiUtils.getRandomPokemon()
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
            }
        }
    }

}