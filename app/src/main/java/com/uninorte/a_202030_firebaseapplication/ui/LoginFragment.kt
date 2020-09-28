package com.uninorte.a_202030_firebaseapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_auth) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyOut","LoginFragment onViewCreated")
        firebaseAuthViewModel.userCreated().observe(getViewLifecycleOwner(), Observer { status ->
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

        firebaseAuthViewModel.logged().observe(getViewLifecycleOwner(), Observer { uid ->
            Log.d("MyOut","LoginFragment logged with "+uid)
            if (uid != ""){
                Toast.makeText(
                    this.requireContext(), "Logged Ok."+uid,
                    Toast.LENGTH_SHORT
                ).show()
                view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
            } else {
                Toast.makeText(
                    this.requireContext(), "Logged failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        buttonSignIn.setOnClickListener {
            Log.d("MyOut","Sign In")
            firebaseAuthViewModel.signIn("a@a.com","1234567")
        }

        buttonSignUp.setOnClickListener {
            Log.d("MyOut","Sign Up")
            firebaseAuthViewModel.signUp("a@a.com","1234567")
        }

        buttonSignInB.setOnClickListener {
            Log.d("MyOut","Sign In")
            firebaseAuthViewModel.signIn("b@b.com","1234567")
        }

        buttonSignUpB.setOnClickListener {
            Log.d("MyOut","Sign Up")
            firebaseAuthViewModel.signUp("b@b.com","1234567")
        }

    }
}