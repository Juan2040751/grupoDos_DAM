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

class HomePicobotellaFragment : Fragment() {
    private var anguloActual: Float = 0f

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_picobotella, container, false)

        val botella: ImageView = view.findViewById(R.id.imgBottle)
        val gifButton: ImageView = view.findViewById(R.id.gifButton)

        gifButton.setOnClickListener {
            rotarImagen(botella)
        }

        // Inicializar el MediaPlayer con el archivo de sonido
        mediaPlayer = MediaPlayer.create(activity, R.raw.background_sound)

        // Configurar el MediaPlayer para reproducir en bucle
        mediaPlayer.isLooping = true

        // Iniciar la reproducción del sonido
        mediaPlayer.start()


        return view
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
        anguloActual = (anguloInicio + anguloAleatorio) % 360
        botella.clearAnimation()
        botella.rotation = anguloActual
        botella.startAnimation(animation)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Detener y Liberar los recursos del MediaPlayer cuando el fragmento se destruye
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
