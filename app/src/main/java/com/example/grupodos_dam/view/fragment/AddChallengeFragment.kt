package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.grupodos_dam.databinding.FragmentAddChallengeBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.viewmodel.ChallengesViewModel


class AddChallengeFragment : Fragment() {
    private lateinit var binding: FragmentAddChallengeBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddChallengeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings()
        observerViewModel()
    }

    private fun settings() {
        checkForm()
        binding.btnSaveChallenge.setOnClickListener {
            saveChallenge()
        }
    }

    private fun observerViewModel(){
        observerListChallenges()
    }

    private fun observerListChallenges() {
        challengesViewModel.getListChallenges()
    }

    private fun saveChallenge(){
        val description = binding.etDescription.text.toString()
        val challenge = Challenge(description = description)
        challengesViewModel.saveChallenge(challenge)
        Toast.makeText(context,"Reto guardado correctamente!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun checkForm() {
        val description = binding.etDescription

        binding.btnSaveChallenge.isEnabled = !description.text.isNotEmpty()
    }

}