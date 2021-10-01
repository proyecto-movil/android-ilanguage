package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class login_inputs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_inputs)

        val btnBack = findViewById<ImageView>(R.id.back_image)

        btnBack.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }
}