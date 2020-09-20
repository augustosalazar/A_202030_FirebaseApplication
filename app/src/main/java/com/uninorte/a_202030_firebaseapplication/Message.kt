package com.uninorte.a_202030_firebaseapplication

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Message (val id: Int? = 0, val text: String? = "")