package com.example.grupodos_dam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.grupodos_dam.R
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.grupodos_dam.databinding.FragmentInstruccionesBinding

class IntruccionesFragment : Fragment() {
    private lateinit var binding: FragmentInstruccionesBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instrucciones, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.navigationContainer)

        // ...

        val backIcon: ImageView = binding.root.findViewById(R.id.backButton)

        backIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                // Navegar de regreso a HomePicobotellaFragment
                //navController.navigate(R.id.action_intruccionesFragment_to_homePicobotellaFragment2)
                activity?.onBackPressedDispatcher?.onBackPressed()
            }.start()
        }
        binding.description1.startAnimation(AnimationUtils.loadAnimation(binding.description1.context, R.anim.scale_text_instrucciones))
        binding.description2.startAnimation(AnimationUtils.loadAnimation(binding.description2.context, R.anim.scale_text_instrucciones))
        binding.winningAnimation.startAnimation(AnimationUtils.loadAnimation(binding.winningAnimation.context, R.anim.scale_challenge))

        return binding.root
    }
}