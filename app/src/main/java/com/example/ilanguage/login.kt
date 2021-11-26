package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnSignIn = findViewById<Button>(R.id.btn_signin)
        val btnSignUp = findViewById<Button>(R.id.btn_signup)

        btnSignIn.setOnClickListener {
            val intent = Intent(this,login_info::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(this,termConditions::class.java)
            startActivity(intent)
        }
    }

}