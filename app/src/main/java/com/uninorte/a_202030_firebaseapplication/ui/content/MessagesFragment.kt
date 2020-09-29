package com.uninorte.a_202030_firebaseapplication.ui.content

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uninorte.a_202030_firebaseapplication.R
import com.uninorte.a_202030_firebaseapplication.model.Message
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseAuthViewModel
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseRealTimeDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_messages.view.*

@AndroidEntryPoint
class MessagesFragment : Fragment(R.layout.fragment_messages) {

    val firebaseAuthViewModel: FirebaseAuthViewModel by activityViewModels()
    val firebaseRealTimeDBViewModelViewModel : FirebaseRealTimeDBViewModel by activityViewModels()
    private val adapter = MessagesAdapter(ArrayList())

    var userUid : String = "_"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyOut","MessagesFragment onViewCreated")

        requireView().message_recycler.adapter = adapter
        requireView().message_recycler.layoutManager = LinearLayoutManager(requireContext())

        firebaseAuthViewModel.logged().observe(getViewLifecycleOwner(), Observer { uid ->
            Log.d("MyOut","MessagesFragment logged with "+uid)
            userUid = uid
            adapter.uid = uid

        })

        firebaseRealTimeDBViewModelViewModel.ldMessageList.observe(getViewLifecycleOwner(), Observer { lista ->
            Log.d("MyOut","Número de mensajes "+lista.size)
            adapter.posts.clear()
            adapter.posts.addAll(lista)
            adapter.notifyDataSetChanged()
            message_recycler.scrollToPosition(lista.size -1)
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