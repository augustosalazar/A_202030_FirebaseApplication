package com.uninorte.a_202030_firebaseapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel : FirebaseRealTimeDBViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuthViewModel.userCreated.observe(getViewLifecycleOwner(), Observer { status ->
            if (status == true){
                Toast.makeText(
                    this.requireContext(), "Authentication Ok.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this.requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        firebaseAuthViewModel.logged.observe(getViewLifecycleOwner(), Observer { status ->
            if (status == true){
                Toast.makeText(
                    this.requireContext(), "Logged Ok.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this.requireContext(), "Logged failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        firebaseRealTimeDBViewModelViewModel.ldMessageList.observe(getViewLifecycleOwner(), Observer { lista ->
            Log.d("MyOut","Número de mensajes "+lista.size)

        })

        buttonWriteTest.setOnClickListener {
            firebaseRealTimeDBViewModelViewModel.writeNewMessage(Message((0..100).random(),"Hola"))
        }

        buttonSignIn.setOnClickListener {
            Log.d("MyOut","Sign In")
            firebaseAuthViewModel.signIn("a@a.com","1234567")
        }

        buttonSignUp.setOnClickListener {
            Log.d("MyOut","Sign Up")
            firebaseAuthViewModel.signUp("a@a.com","1234567")
        }

        buttonLogOut.setOnClickListener {
            firebaseAuthViewModel.logOut()
        }
    }
}