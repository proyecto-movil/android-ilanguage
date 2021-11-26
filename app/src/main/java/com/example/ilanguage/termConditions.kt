package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class termConditions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_conditions)

        val btnAgree = findViewById<Button>(R.id.button_accept)
        val btnDisagree = findViewById<Button>(R.id.button_reject)

        btnAgree.setOnClickListener {
            val intent = Intent(this,select_signup::class.java)
            startActivity(intent)
        }

        btnDisagree.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }
}