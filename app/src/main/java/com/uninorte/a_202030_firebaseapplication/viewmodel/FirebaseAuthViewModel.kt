package com.uninorte.a_202030_firebaseapplication.viewmodel

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class FirebaseAuthViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var logged = MutableLiveData<Boolean>()
    var userCreated = MutableLiveData<Boolean>()

    init {
        auth = Firebase.auth
        logged.value = false
    }


    fun signUp(email: String, password : String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MyOut", "createUserWithEmail:success")
                    val user = auth.currentUser
                    userCreated.value = true;
                } else {
                    Log.d("MyOut", "createUserWithEmail:failure", task.exception)
                    userCreated.value = false;
                }
            }
    }

    fun signIn(email: String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("MyOut", "signInWithEmailAndPassword:success "+ auth.currentUser!!.email)
                    logged.value = true
                } else {
                    logged.value = false
                    Log.d("MyOut", "signInWithEmailAndPassword:failure", task.exception)
                }
            }
    }

    fun logOut(){
        Firebase.auth.signOut()
    }

}