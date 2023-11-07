package com.example.grupodos_dam.view

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.grupodos_dam.R
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var anguloActual: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var botella: ImageView = findViewById (R.id.imgBottle);//pasar la imagen deseada
        var rotacion: Float= botella.rotation
        rotarImagen(botella);//al presionar el boton
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