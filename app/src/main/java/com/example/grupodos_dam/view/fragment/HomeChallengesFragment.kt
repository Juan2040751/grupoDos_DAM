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
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grupodos_dam.R
import com.example.grupodos_dam.databinding.FragmentHomeChallengesBinding
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.view.adapter.ChallengesAdapter
import com.example.grupodos_dam.view.viewholder.ChallengesViewHolder
import com.example.grupodos_dam.viewmodel.ChallengesViewModel
import androidx.appcompat.app.AppCompatActivity

class HomeChallengesFragment : Fragment(), ChallengesViewHolder.EditChallengeListener {
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
            val adapter = ChallengesAdapter(listaChallenges, navController, this)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    override fun onEditChallengeClick(challenge: Challenge) {
        showUpdateChallengeDialog(challenge, findNavController())
    }

    private fun settings(navController: NavController) {
        binding.fbagregar.setOnClickListener {
            //findNavController().navigate(R.id.action_homeChallengesFragment_to_addChallengeFragment)
            showAddChallengeDialog(navController)
        }
        setupToolbar()
    }

    private fun setupToolbar() {

        val backIcon: ImageView = binding.root.findViewById(R.id.backButton_challenges)

        backIcon.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(200).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                // Navegar de regreso a HomePicobotellaFragment
                //navController.navigate(R.id.action_intruccionesFragment_to_homePicobotellaFragment2)
                activity?.onBackPressedDispatcher?.onBackPressed()
            }.start()
        }


    }

    fun showAddChallengeDialog(navController: NavController) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_challenge)

        dialog.window?.setLayout(1000, 1000)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

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

    private fun showUpdateChallengeDialog(challenge: Challenge, navController: NavController) {
        val dialog = Dialog(binding.root.context)
        dialog.setContentView(R.layout.dialog_update_challenge)

        val editTextChallenge = dialog.findViewById<EditText>(R.id.edit_text_challenge)
        val buttonCancel = dialog.findViewById<Button>(R.id.edit_challenge_button_cancel)
        val buttonEdit = dialog.findViewById<Button>(R.id.edit_challenge_button_edit)

        editTextChallenge.setText(challenge.description)

        editTextChallenge.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonEdit.isEnabled = !s.isNullOrBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        buttonEdit.setOnClickListener {
            val updatedChallenge = editTextChallenge.text.toString()

            var result = false

            // Update the challenge using the ChallengesViewModel
            result = updateChallenge(challenge.id, updatedChallenge)

            if(result){
                Toast.makeText(context,"Reto actualizado correctamente!", Toast.LENGTH_SHORT).show()
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

    private fun updateChallenge(id: Int, description: String): Boolean {
        val challenge = Challenge(id = id, description = description)
        challengesViewModel.updateChallenge(challenge)
        return true;
    }



}