package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentChallengeEditBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.viewmodel.ChallengesViewModel

class ChallengeEditFragment : Fragment() {
    private lateinit var binding: FragmentChallengeEditBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenge

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeEditBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataChallenge()
        settings()

    }

    private fun settings(){
        binding.btnEdit.setOnClickListener {
            updateChallenge()
        }
    }

    private fun dataChallenge(){
        val receivedBundle = arguments
        receivedChallenge = receivedBundle?.getSerializable("dataChallenge") as Challenge
        binding.etDescription.setText(receivedChallenge.description)
    }

    private fun updateChallenge(){
        val description = binding.etDescription.text.toString()
        val challenge = Challenge(receivedChallenge.id, description = description)
        challengesViewModel.updateChallenge(challenge)
        findNavController().navigate(R.id.action_ChallengeEditFragment_to_homeChallengesFragment)

    }


}