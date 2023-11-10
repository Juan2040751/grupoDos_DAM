package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentHomeChallengesBinding
import com.example.grupodos_dam.view.adapter.ChallengesAdapter
import com.example.grupodos_dam.viewmodel.ChallengesViewModel

class HomeChallengesFragment : Fragment() {
    private lateinit var binding: FragmentHomeChallengesBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings()
        observerViewModel()
    }

    private fun observerViewModel() {
       observerListChallenges()
    }

    private fun observerListChallenges() {
        challengesViewModel.getListChallenges()
        challengesViewModel.listChallenges.observe(viewLifecycleOwner){ listaChallenges->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(listaChallenges, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    private fun settings() {
        binding.fbagregar.setOnClickListener {
            findNavController().navigate(R.id.action_homeChallengesFragment_to_addChallengeFragment)
        }
    }

}