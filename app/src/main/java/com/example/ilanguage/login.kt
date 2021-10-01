package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnSignIn = findViewById<Button>(R.id.btn_signin)
        val btnSignUp = findViewById<Button>(R.id.btn_signup)

        btnSignIn.setOnClickListener {
            val intent = Intent(this,login_inputs::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(this,select_signup::class.java)
            startActivity(intent)
        }

    }
}