package com.uninorte.a_202030_firebaseapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.model.Message
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.android.synthetic.main.fragment_messages.*

@AndroidEntryPoint
class MessagesFragment : Fragment(R.layout.fragment_messages) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel : FirebaseRealTimeDBViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuthViewModel.logged.observe(getViewLifecycleOwner(), Observer { uid ->
            if (uid == ""){
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

        buttonLogOut.setOnClickListener {
            firebaseAuthViewModel.logOut()
        }
    }
}