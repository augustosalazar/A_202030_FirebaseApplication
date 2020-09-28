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

    var userUid : String = "_"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyOut","MessagesFragment onViewCreated")

        firebaseAuthViewModel.logged().observe(getViewLifecycleOwner(), Observer { uid ->
            Log.d("MyOut","MessagesFragment logged with "+uid)
            userUid = uid

        })

        firebaseRealTimeDBViewModelViewModel.ldMessageList.observe(getViewLifecycleOwner(), Observer { lista ->
            Log.d("MyOut","Número de mensajes "+lista.size)

        })

        buttonWriteTest.setOnClickListener {
            userUid = firebaseAuthViewModel.logged().value!!
            Log.d("MyOut","Writing message for user <"+userUid+">")
            firebaseRealTimeDBViewModelViewModel.writeNewMessage(
                Message((0..100).random(),"Hola", userUid)
            )
        }

        buttonLogOut.setOnClickListener {
            firebaseAuthViewModel.logOut()
            view.findNavController().navigate(R.id.action_messagesFragment_to_authActivity)
        }
    }
}