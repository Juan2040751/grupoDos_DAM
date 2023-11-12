package com.example.grupodos_dam.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.grupodos_dam.R
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import com.example.grupodos_dam.databinding.ChallengesActivityBinding

class ChallengesActivity : AppCompatActivity() {

    lateinit var binding:ChallengesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.challenges_activity)

        settings()
    }

    private fun settings(){
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = binding.challengesToolbar.challengesToolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Retos"

    }
}