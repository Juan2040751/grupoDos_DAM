package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.grupodos_dam.R
import android.widget.ImageView
import android.view.animation.RotateAnimation
import kotlin.random.Random
import android.media.MediaPlayer

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation

class HomePicobotellaFragment : Fragment() {
    private var anguloActual: Float = 0f

    private lateinit var mp_background: MediaPlayer
    private lateinit var mp_spinning_bottle: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_picobotella, container, false)

        val img_botella: ImageView = view.findViewById(R.id.imgBottle)
        val gifButton: ImageView = view.findViewById(R.id.gifButton)

        gifButton.setOnClickListener {
            rotarImagen(img_botella)
        }

        // Inicializar el MediaPlayer con el archivo de sonido
        mp_background = MediaPlayer.create(activity, R.raw.background_sound)
        mp_spinning_bottle = MediaPlayer.create(activity, R.raw.spinning_bottle)

        // Configurar el MediaPlayer para reproducir en bucle
        mp_background.isLooping = true

        // Iniciar la reproducción del sonido
        mp_background.start()


        return view
    }

    private fun rotarImagen(botella: ImageView) {
        mp_background.pause()
        val anguloInicio: Float = anguloActual
        val anguloAleatorio: Float = Random.nextInt(3600).toFloat()
        val animation  =  RotateAnimation(
            anguloInicio, anguloAleatorio,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )

        animation.duration = (2000 + Random.nextInt(3000)).toLong()
        animation.repeatCount = 0
        animation.fillAfter = true
        animation.interpolator = AccelerateDecelerateInterpolator()
        anguloActual = anguloAleatorio % 360

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                mp_spinning_bottle.start()
            }

            override fun onAnimationEnd(animation: Animation?) {
                mp_spinning_bottle.pause()
                mp_background.start()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        botella.startAnimation(animation)


    }

    override fun onDestroy() {
        super.onDestroy()

        // Detener y Liberar los recursos del MediaPlayer cuando el fragmento se destruye
        mp_background.stop()
        mp_background.release()
    }
}
