package com.example.grupodos_dam.view

import android.content.Intent
import com.example.grupodos_dam.R
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import pl.droidsonroids.gif.GifImageView

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 5000 // 5 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ttb = AnimationUtils.loadAnimation(  this, R.anim.ttb);
        val stb = AnimationUtils.loadAnimation(  this, R.anim.stb);
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val gif = findViewById<GifImageView>(R.id.gifImageView)
        titleTextView.startAnimation(ttb)
        gif.startAnimation(stb)
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


