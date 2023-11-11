package com.example.grupodos_dam.view

import android.content.Intent
import com.example.grupodos_dam.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 1000 // 5 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Usar una coroutine para gestionar el tiempo de espera
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashTimeOut)

            // Creamos un Intent para iniciar la MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Cerramos la SplashActivity
            finish()
        }
    }
}


