package com.example.grupodos_dam.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.ItemChallengeBinding
import com.example.grupodos_dam.model.Challenge

class ChallengesViewHolder (binding: ItemChallengeBinding, navController: NavController):RecyclerView.ViewHolder(binding.root){
    val bindingItem = binding
    val navController = navController

    fun setItemChallenge(challenge: Challenge){
        bindingItem.tvDescription.text = challenge.description
        bindingItem.cvChallenges.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("clave",challenge)
            //navController.navigate(R.id.action_homeChallengesFragment_to_EditChallengeFragment,bundle)
        }
    }
}