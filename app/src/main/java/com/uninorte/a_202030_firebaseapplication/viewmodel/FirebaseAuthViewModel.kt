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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uninorte.a_202030_firebaseapplication.model.Message
import com.uninorte.a_202030_firebaseapplication.model.User
import com.uninorte.a_202030_firebaseapplication.repository.FirebaseAuthRepository
import javax.inject.Inject


class FirebaseAuthViewModel
@ViewModelInject constructor(
    private val repository: FirebaseAuthRepository
): ViewModel() {

    fun logged() = repository.logged

    fun userCreated() = repository.userCreated

    fun signUp(email: String, password : String){
        repository.signUp(email,password)
    }

    fun signIn(email: String, password : String){
        repository.signIn(email,password)
    }

    fun logOut(){
        repository.logOut()
    }

}