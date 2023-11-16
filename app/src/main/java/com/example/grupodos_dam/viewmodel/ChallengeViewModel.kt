package com.example.grupodos_dam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.model.Pokemon
import com.example.grupodos_dam.model.PokemonListResponse
import com.example.grupodos_dam.repository.ChallengesRepository
//import com.example.grupodos_dam.webservice.Pokemon
import com.example.grupodos_dam.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ChallengesViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val challengesRepository = ChallengesRepository(context)

    private val _listChallenges = MutableLiveData<MutableList<Challenge>>()

    private val _listPokemons = MutableLiveData<List<Pokemon>>() //this
    val listPokemons: LiveData<List<Pokemon>> = _listPokemons //this
    val listChallenges: LiveData<MutableList<Challenge>> get() = _listChallenges

    private var _randomChallenge = MutableLiveData<Challenge>()
    val randomChallenge: LiveData<Challenge> get() = _randomChallenge

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

    fun getPokemons(){
        viewModelScope.launch {
            _listPokemons.value = challengesRepository.getPokemons()
        }
    }
}