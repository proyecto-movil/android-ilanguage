package com.example.ilanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class profile_teacher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teacher)

        val btReservar=findViewById<Button>(R.id.btReservar)
        btReservar.setOnClickListener {

        }
    }
}