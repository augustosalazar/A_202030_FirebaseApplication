package com.uninorte.a_202030_firebaseapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uninorte.a_202030_firebaseapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setSupportActionBar(findViewById(R.id.toolbar))
        baseContext

    }

}