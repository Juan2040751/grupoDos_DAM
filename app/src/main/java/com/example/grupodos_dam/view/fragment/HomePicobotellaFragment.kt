package com.example.grupodos_dam.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.grupodos_dam.R
import kotlin.random.Random

class HomePicobotellaFragment : Fragment() {
    private var anguloActual: Float = 0f

    private lateinit var mp_background: MediaPlayer
    private lateinit var mp_spinning_bottle: MediaPlayer
    private lateinit var tv_countdown: TextView
    private lateinit var gifButton: ImageView
    private lateinit var img_botella: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_picobotella, container, false)

        img_botella = view.findViewById(R.id.imgBottle)
        gifButton = view.findViewById(R.id.gifButton)
        tv_countdown = view.findViewById(R.id.tv_countdown)
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
        gifButton.visibility = View.GONE
        mp_background.pause()
        val anguloInicio: Float = anguloActual
        val anguloAleatorio: Float = Random.nextInt(3600).toFloat()
        val animation = RotateAnimation(
            anguloInicio, anguloAleatorio,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )

        animation.duration = (2000 + Random.nextInt(3000)).toLong()
        animation.repeatCount = 0
        animation.fillAfter = true
        animation.interpolator = AccelerateDecelerateInterpolator()
        anguloActual = anguloAleatorio % 360

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                mp_spinning_bottle.start()
            }

            override fun onAnimationEnd(animation: Animation?) {
                mp_spinning_bottle.pause()
                object : CountDownTimer(4000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        tv_countdown.setText("" + millisUntilFinished / 1000)
                    }

                    override fun onFinish() {
                        tv_countdown.setText("")
                        gifButton.visibility = View.VISIBLE
                        //aqui inicia PB-12
                    }
                }.start()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        botella.startAnimation(animation)


    }

    override fun onStop() {
        super.onStop()
        mp_spinning_bottle.pause()
        mp_background.pause()// verificar logica
    }

    override fun onDestroy() {
        super.onDestroy()

        // Detener y Liberar los recursos del MediaPlayer cuando el fragmento se destruye
        mp_background.stop()
        mp_background.release()
    }
}
