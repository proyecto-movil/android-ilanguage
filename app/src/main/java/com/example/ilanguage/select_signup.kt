package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView

class select_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_signup)


        val btnBack = findViewById<ImageView>(R.id.back_image_selection)
        val btnStudent = findViewById<CardView>(R.id.card_student)
        val btnTeacher = findViewById<CardView>(R.id.card_teacher)

        var optionRegister: Int = 0

        btnBack.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }

        btnStudent.setOnClickListener {
            val intent = Intent(this,details_signup::class.java)
            optionRegister = 2
            intent.putExtra("optionRegister",optionRegister)
            startActivity(intent)
        }
        btnTeacher.setOnClickListener {
            val intent = Intent(this,details_signup::class.java)
            optionRegister = 1
            intent.putExtra("optionRegister",optionRegister)
            startActivity(intent)
        }

    }
}