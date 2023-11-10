package com.example.grupodos_dam.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.grupodos_dam.R
import com.example.grupodos_dam.view.fragment.HomePicobotellaFragment
import com.example.grupodos_dam.view.fragment.SplashPicobotellaFragment
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mostrar el fragmento de inicio (SplashPicobotellaFragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SplashPicobotellaFragment())
            .commit()

        // Espera 5 segundos y luego reemplaza el fragmento de inicio con el fragmento principal (HomeFragment)
        coroutineScope.launch {
            delay(5000) // 5000 milisegundos (5 segundos)
            replaceFragment(HomePicobotellaFragment())
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Cancela la corutina cuando la actividad se destruye
    }
}

