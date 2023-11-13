package com.example.grupodos_dam.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentHomePicobotellaBinding
import kotlin.random.Random
import android.os.Build
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.viewmodel.ChallengesViewModel
import com.bumptech.glide.Glide
import com.example.grupodos_dam.webservice.Pokemon
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.grupodos_dam.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePicobotellaFragment : Fragment() {
    private lateinit var binding: FragmentHomePicobotellaBinding
    private lateinit var navController: NavController

    private var anguloActual: Float = 0f
    private lateinit var toolbar: Toolbar
    private lateinit var mp_background: MediaPlayer
    private lateinit var mp_spinning_bottle: MediaPlayer
    private lateinit var tv_countdown: TextView
    private lateinit var gifButton: ImageView
    private lateinit var presionameTextView: TextView
    private lateinit var img_botella: ImageView
    private lateinit var progressBar: ProgressBar
    private var sound_background_play: Boolean = true
    private var sound_bottle_play: Boolean = false
    private val challengesViewModel: ChallengesViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
            if(!isGranted){
                Toast.makeText(context,"Necesitas aceptar los Permisos",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_picobotella, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.navigationContainer)
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_home_picobotella, container, false)
        val view = binding.root

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
        mp_background.isLooping = true

        //iconos toolbar
        val starIcon: ImageView = view.findViewById(R.id.starIcon)
        val audioIcon: ImageView = view.findViewById(R.id.audioUpIcon)
        val controlIcon: ImageView = binding.root.findViewById(R.id.controlIcon)
        val addIcon: ImageView = view.findViewById(R.id.addIcon)
        val shareIcon: ImageView = view.findViewById(R.id.shareIcon)

        // Iniciar la reproducción del sonido
        if (sound_background_play) {
            mp_background.start()
        }else{
            audioIcon.setImageResource(R.drawable.ic_volume_off)
        }

        // Escuchas componentes toolbar
        starIcon.setOnClickListener {
            starIcon_handle(it)
        }
        audioIcon.setOnClickListener {
            audioIcon_handle(it, audioIcon)
        }
        controlIcon.setOnClickListener {
            controlIcon_handle(it)
        }

        addIcon.setOnClickListener {
            addIcon_handle(it)
        }

        shareIcon.setOnClickListener {
            shareIcon_handle(it)
        }

        //Para refrescar el reto aleatorio
        challengesViewModel.getRandomChallenge()
        challengesViewModel.getRandomPokemon()

        return view
    }
    private fun starIcon_handle(it: View) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
            // Restaura la escala original después de la animación
            it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

            // Abre la URL en un navegador web
            val url =
                "https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }.start()
    }

    private fun audioIcon_handle(it: View, audioIcon: ImageView) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
            it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            sound_background_play = !mp_background.isPlaying
            if (mp_background.isPlaying) {
                mp_background.pause()
                audioIcon.setImageResource(R.drawable.ic_volume_off)
            } else {
                mp_background.start()
                audioIcon.setImageResource(R.drawable.ic_volume_up)
            }
        }.start()
    }

    private fun controlIcon_handle(it: View) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
            it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

            // Navegar de regreso a HomePicobotellaFragment
            navController.navigate(R.id.action_homePicobotellaFragment2_to_intruccionesFragment)
        }.start()
    }

    private fun addIcon_handle(it: View) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
            it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            //startActivity(Intent(this.context, ChallengesActivity::class.java))
            navController.navigate(R.id.action_homePicobotellaFragment2_to_homeChallengesFragment)
        }.start()
    }
    private fun shareIcon_handle(it: View) {
        it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
            it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "App pico botella\nSolo los valientes lo juegan !!\nhttps://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permisos()
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
                sound_bottle_play = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                mp_spinning_bottle.pause()
                progressBar.visibility = View.VISIBLE;
                progressBar.isIndeterminate = false
                sound_bottle_play = false

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
                        showRandomChallengeDialog()

                    }
                }.start()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        botella.startAnimation(animation)
    }

    override fun onResume() {
        super.onResume()
        if (sound_background_play) {
            mp_background.start()
        }
        if (sound_bottle_play) {
            mp_spinning_bottle.start()
        }
    }

    override fun onStop() {
        super.onStop()

        if (sound_background_play) {
            mp_background.pause()
        }
        if (sound_bottle_play) {
            mp_spinning_bottle.pause()
        }

    }
    override fun onDestroy() {
        super.onDestroy()

        // Detener y Liberar los recursos del MediaPlayer cuando el fragmento se destruye
        mp_background.stop()
        mp_background.release()
    }

    private fun permisos() {
        solicitudPermisoInternet()
    }

    private fun solicitudPermisoInternet(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                //Todo:Cuando ya se ha aceptado el permiso
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.INTERNET
                )== PackageManager.PERMISSION_GRANTED ->{
                }

                //Todo: Cuando se pide el permiso y se rechaza
                shouldShowRequestPermissionRationale(android.Manifest.permission.INTERNET) ->{
                    AlertDialog.Builder(context)
                        .setTitle("Permisos de Internet")
                        .setMessage("Acepta los permisos")
                        .setPositiveButton("SI"){_,_ ->
                            requestPermissionLauncher.launch(android.Manifest.permission.INTERNET)
                        }
                        .setNegativeButton("No"){ _, _ -> }.show()
                }
                else ->{
                    //todo: cuando se entra a la camara por primera vez
                    requestPermissionLauncher.launch(android.Manifest.permission.INTERNET)
                }
            }
        }
    }
    
    fun showRandomChallengeDialog() {

        challengesViewModel.getRandomChallenge()
        challengesViewModel.getRandomPokemon()

        val challenge:Challenge? = challengesViewModel.randomChallenge.value

        val pokemon:Pokemon? = challengesViewModel.randomPokemon.value

        //GlobalScope.launch(Dispatchers.Main) {
        //    pokemon = ApiUtils.getRandomPokemon()
        //}


        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_random_challenge)

        dialog.window?.setLayout(1100, 1200)

        val constraintLayout = dialog.findViewById<ConstraintLayout>(R.id.clRandonChallenge)
        constraintLayout.setBackgroundColor(Color.TRANSPARENT)

        val tvRandomChallenge = dialog.findViewById<TextView>(R.id.tv_random_challenge)
        val ivRandomPokemon = dialog.findViewById<ImageView>(R.id.iv_random_pokemon)
        val buttonCerrar = dialog.findViewById<Button>(R.id.btn_close_random_challenge)


        if (challenge != null) {
            tvRandomChallenge.text = challenge.description
        }

        if (pokemon != null) {
            Toast.makeText(context, pokemon.name, Toast.LENGTH_SHORT).show()
            Glide.with(requireActivity()).load(pokemon.img).into(ivRandomPokemon)
        }
        else{
            Toast.makeText(context, "No hay pokemones disponibles", Toast.LENGTH_SHORT).show()
        }

        buttonCerrar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

}
