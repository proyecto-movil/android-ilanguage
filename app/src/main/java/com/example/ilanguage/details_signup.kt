package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class details_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_signup)

        val btnContinue = findViewById<Button>(R.id.btn_continue_signup)
        val btnBack = findViewById<ImageView>(R.id.back_image3)

        btnContinue.setOnClickListener {
            val intent = Intent(this,activity_details_personal::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }
}

