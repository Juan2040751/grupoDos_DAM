package com.example.grupodos_dam.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.grupodos_dam.R
import com.example.grupodos_dam.view.fragment.HomePicobotellaFragment
import com.example.grupodos_dam.view.fragment.SplashPicobotellaFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mostrar el fragmento de inicio (SplashPicobotellaFragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SplashPicobotellaFragment())
            .commit()

        // Espera 5 segundos y luego reemplaza el fragmento de inicio con el fragmento principal (HomeFragment)
        Handler().postDelayed({
            replaceFragment(HomePicobotellaFragment())
        }, 5000) // 5000 milisegundos (5 segundos)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


}