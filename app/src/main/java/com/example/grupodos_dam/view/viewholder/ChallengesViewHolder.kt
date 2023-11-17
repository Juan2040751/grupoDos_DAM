package com.example.grupodos_dam.view.viewholder

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.ItemChallengeBinding
import com.example.grupodos_dam.model.Challenge

class ChallengesViewHolder(
    binding: ItemChallengeBinding,
    navController: NavController,
    private val editChallengeListener: EditChallengeListener,
    private val deleteChallengeListener: DeleteChallengeListener
) : RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    val navController = navController

    fun setItemChallenge(challenge: Challenge) {
        bindingItem.tvDescription.text = challenge.description
        bindingItem.cvChallenges.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("clave", challenge)
            //navController.navigate(R.id.action_homeChallengesFragment_to_EditChallengeFragment,bundle)
        }
        bindingItem.ivEditChallenge.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
            editChallengeListener.onEditChallengeClick(challenge)
        }
        bindingItem.ivDelete.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
            deleteChallengeListener.onDeleteChallengeClick(challenge)
        }

        bindingItem.cvChallenges.startAnimation(
            AnimationUtils.loadAnimation(
                bindingItem.cvChallenges.context,
                R.anim.scale_challenge
            )
        )
    }

    interface EditChallengeListener {
        fun onEditChallengeClick(challenge: Challenge)
    }

    interface DeleteChallengeListener {
        fun onDeleteChallengeClick(challenge: Challenge)
    }

}