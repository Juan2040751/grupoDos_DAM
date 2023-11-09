package com.example.grupodos_dam.view

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var anguloActual: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var botella: ImageView = findViewById (R.id.imgBottle);//pasar la imagen deseada
        var rotacion: Float= botella.rotation
        rotarImagen(botella);//al presionar el boton

        //ImageView toolbar
        val starIcon: ImageView = findViewById(R.id.starIcon)
        val audioUpIcon: ImageView = findViewById(R.id.audioUpIcon)
        val controlIcon: ImageView = findViewById(R.id.controlIcon)
        val addIcon: ImageView = findViewById(R.id.addIcon)
        val shareIcon: ImageView = findViewById(R.id.shareIcon)

        //Escuchas componentes toolbar
        starIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                // Restaura la escala original después de la animación
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                showToast("Click en estrella")
            }.start()
        }

        audioUpIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                showToast("Click en sonido")
            }.start()
        }

        controlIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                showToast("Click en control")
            }.start()
        }

        addIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                showToast("Click en añadir")
            }.start()
        }

        shareIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                showToast("Click en compartir")
            }.start()
        }
    }

    //esta funcion sólo para probar clicks
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun rotarImagen(botella: ImageView) {

        val anguloInicio: Float = anguloActual
        val anguloAleatorio: Float = Random.nextInt(360).toFloat()
        val animation = RotateAnimation(
            anguloInicio, anguloAleatorio,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )

        animation.duration = 2000
        animation.repeatCount = 0
        anguloActual = (anguloInicio + anguloAleatorio)%360
        botella.clearAnimation()
        botella.setRotation(anguloActual)
        botella.startAnimation(animation)

    }



}