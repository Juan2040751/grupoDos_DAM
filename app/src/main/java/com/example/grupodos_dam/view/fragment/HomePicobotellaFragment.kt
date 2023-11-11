package com.example.grupodos_dam.view.fragment

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.grupodos_dam.R
import kotlin.random.Random

class HomePicobotellaFragment : Fragment() {
    private var anguloActual: Float = 0f

    private lateinit var mp_background: MediaPlayer
    private lateinit var mp_spinning_bottle: MediaPlayer
    private lateinit var tv_countdown: TextView
    private lateinit var gifButton: ImageView
    private lateinit var presionameTextView: TextView
    private lateinit var img_botella: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_picobotella, container, false)
        img_botella = view.findViewById(R.id.imgBottle)
        gifButton = view.findViewById(R.id.gifButton)
        tv_countdown = view.findViewById(R.id.tv_countdown)
        progressBar = view.findViewById(R.id.progressBar)
        gifButton.setOnClickListener {
            rotarImagen(img_botella)

        }
        presionameTextView = view.findViewById(R.id.presionameTextView)

        // Inicializar el MediaPlayer con el archivo de sonido
        mp_background = MediaPlayer.create(activity, R.raw.background_sound)
        mp_spinning_bottle = MediaPlayer.create(activity, R.raw.spinning_bottle)

        // Configurar el MediaPlayer para reproducir en bucle
        mp_background.isLooping = true

        // Iniciar la reproducción del sonido
        mp_background.start()
        val starIcon: ImageView = view.findViewById(R.id.starIcon)
        val audioIcon: ImageView = view.findViewById(R.id.audioUpIcon)
        val controlIcon: ImageView = view.findViewById(R.id.controlIcon)
        val addIcon: ImageView = view.findViewById(R.id.addIcon)
        val shareIcon: ImageView = view.findViewById(R.id.shareIcon)

        // Escuchas componentes toolbar
        starIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                // Restaura la escala original después de la animación
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                Toast.makeText(getActivity(),"Click en estrella",Toast.LENGTH_SHORT).show();
            }.start()
        }

        audioIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                if (mp_background.isPlaying) {
                    mp_background.pause()
                    audioIcon.setImageResource(R.drawable.ic_volume_off)
                } else {
                    mp_background.start()
                    audioIcon.setImageResource(R.drawable.ic_volume_up)
                }
            }.start()
        }

        controlIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                Toast.makeText(getActivity(),"Click en control",Toast.LENGTH_SHORT).show();
            }.start()
        }

        addIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                Toast.makeText(getActivity(),"Click en añadir",Toast.LENGTH_SHORT).show();

            }.start()
        }

        shareIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "App pico botella\nSolo los valientes lo juegan !!\nhttps://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }.start()
        }

        return view
    }

    private fun rotarImagen(botella: ImageView) {
        gifButton.visibility = View.GONE
        presionameTextView.visibility = View.INVISIBLE
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
                progressBar.visibility = View.VISIBLE;
                progressBar.isIndeterminate = false


                object : CountDownTimer(4000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        tv_countdown.setText("" + millisUntilFinished / 1000)
                        progressBar.incrementProgressBy(33)
                    }

                    override fun onFinish() {
                        tv_countdown.setText("")
                        gifButton.visibility = View.VISIBLE
                        presionameTextView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
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
