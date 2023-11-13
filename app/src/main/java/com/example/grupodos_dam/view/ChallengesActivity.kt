package com.example.grupodos_dam.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.grupodos_dam.R
import androidx.databinding.DataBindingUtil
import com.example.grupodos_dam.databinding.ChallengesActivityBinding
import com.example.grupodos_dam.view.fragment.HomeChallengesFragment

class ChallengesActivity : AppCompatActivity() {

    lateinit var binding:ChallengesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        binding = DataBindingUtil.setContentView(this,R.layout.challenges_activity)

        settings()
    }
    private fun settings(){
        supportFragmentManager.beginTransaction().replace(R.id.navigationChallengesContainer, HomeChallengesFragment()).commit()
    }

}