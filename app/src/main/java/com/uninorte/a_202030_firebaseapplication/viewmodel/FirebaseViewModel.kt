package com.uninorte.a_202030_firebaseapplication.viewmodel

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class FirebaseViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth


    init {
        auth = Firebase.auth
    }

    fun signUp(email: String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MyOut", "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w("MyOut", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun signIn(email: String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("MyOut", "signInWithEmailAndPassword:success "+ auth.currentUser!!.email)

                } else {
                    Log.w("MyOut", "signInWithEmailAndPassword:failure", task.exception)
                }
            }
    }

}