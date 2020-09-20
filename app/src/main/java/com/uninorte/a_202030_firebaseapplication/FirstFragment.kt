package com.uninorte.a_202030_firebaseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.uninorte.a_202030_firebaseapplication.viewmodel.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    val firebaseViewModel: FirebaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            firebaseViewModel.signUp("a@a.com","1234567")
        }

        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            firebaseViewModel.signIn("a@a.com","1234567")
        }
    }
}