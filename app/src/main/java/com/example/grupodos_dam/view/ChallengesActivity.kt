package com.example.grupodos_dam.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grupodos_dam.R
import androidx.databinding.DataBindingUtil
import com.example.grupodos_dam.databinding.ChallengesActivityBinding
import com.example.grupodos_dam.view.fragment.HomeChallengesFragment

class ChallengesActivity : AppCompatActivity() {

    lateinit var binding:ChallengesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.challenges_activity)

        settings()
    }
    private fun settings(){
        supportFragmentManager.beginTransaction().replace(R.id.navigationChallengesContainer, HomeChallengesFragment()).commit()

        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = binding.challengesToolbar.challengesToolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Retos"

    }
}