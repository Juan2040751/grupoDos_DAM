package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentChallengeDetailsBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.viewmodel.ChallengesViewModel


class ChallengeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentChallengeDetailsBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenge

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings()
        dataChallenge()
    }

    private fun settings() {

    }

    private fun deleteChallenge() {
        challengesViewModel.deleteChallenge(receivedChallenge)
        challengesViewModel.getListChallenges()
        findNavController().popBackStack()
    }

    private fun dataChallenge(){
        val receivedBundle = arguments
        receivedChallenge = receivedBundle?.getSerializable("clave") as Challenge
        binding.tvChallenge.text ="${receivedChallenge.description}"
    }
}