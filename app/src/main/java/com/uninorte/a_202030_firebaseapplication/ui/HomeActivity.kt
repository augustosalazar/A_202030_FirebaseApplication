package com.uninorte.a_202030_firebaseapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uninorte.a_202030_firebaseapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar2))

    }

}