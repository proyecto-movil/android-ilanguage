package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class select_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_signup)


        val btnBack = findViewById<ImageView>(R.id.back_image_selection)

        btnBack.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
    }
}