package com.example.grupodos_dam.view.fragment

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentHomeChallengesBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.view.adapter.ChallengesAdapter
import com.example.grupodos_dam.viewmodel.ChallengesViewModel

class HomeChallengesFragment : Fragment() {
    private lateinit var binding: FragmentHomeChallengesBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        settings(navController)
        observerViewModel(navController)
    }

    private fun observerViewModel(navController: NavController) {
       observerListChallenges(navController)
    }

    private fun observerListChallenges(navController: NavController) {

        challengesViewModel.getListChallenges()
        challengesViewModel.listChallenges.observe(viewLifecycleOwner){ listaChallenges->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(listaChallenges, navController)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    private fun settings(navController: NavController) {
        binding.fbagregar.setOnClickListener {
            //findNavController().navigate(R.id.action_homeChallengesFragment_to_addChallengeFragment)
            showAddChallengeDialog(navController)
        }

        //binding.challengesBack.setOnClickListener{
            //findNavController().navigate(R.id.action_homeChallengesFragment_to_homePicobotellaFragment2)
            //findNavController().popBackStack()
        //}
    }

    fun showAddChallengeDialog(navController: NavController) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_challenge)

        dialog.window?.setLayout(1000, 800)

        val editTextChallenge = dialog.findViewById<EditText>(R.id.edit_text_challenge)
        val buttonCancel = dialog.findViewById<Button>(R.id.add_challenge_button_cancel)
        val buttonAdd = dialog.findViewById<Button>(R.id.add_challenge_button_add)

        editTextChallenge.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonAdd.isEnabled = !s.isNullOrBlank()

                if(s.isNullOrBlank()){
                    buttonAdd.setBackgroundColor(resources.getColor(R.color.grey))
                }
                else{
                    buttonAdd.setBackgroundColor(resources.getColor(R.color.orange))
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        buttonAdd.setOnClickListener {
            val challenge = editTextChallenge.text.toString()

            var result = false

            result = saveChallenge(challenge)

            if(result){
                Toast.makeText(context,"Reto guardado correctamente!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()

            observerViewModel(navController)

        }


        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun saveChallenge(description: String): Boolean {
        val challenge = Challenge(description = description)
        challengesViewModel.saveChallenge(challenge)
        //findNavController().popBackStack()
        return true;
    }


}