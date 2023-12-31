package com.example.grupodos_dam.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grupodos_dam.databinding.ItemChallengeBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.view.viewholder.ChallengesViewHolder

class ChallengesAdapter (private val listChallenge:MutableList<Challenge>, private val navController: NavController, private val editChallengeListener: ChallengesViewHolder.EditChallengeListener, private val deleteChallengeListener: ChallengesViewHolder.DeleteChallengeListener):RecyclerView.Adapter<ChallengesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengesViewHolder {
        val binding = ItemChallengeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChallengesViewHolder(binding,navController, editChallengeListener, deleteChallengeListener)
    }

    override fun onBindViewHolder(holder: ChallengesViewHolder, position: Int) {
        val challenge = listChallenge[position]
        holder.setItemChallenge(challenge)

    }

    override fun getItemCount(): Int {
        return listChallenge.size
    }

}