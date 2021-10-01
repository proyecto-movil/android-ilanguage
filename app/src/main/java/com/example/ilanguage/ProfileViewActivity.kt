package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfileViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)
        val btEditProfile = findViewById<Button>(R.id.btEditProfile)
        btEditProfile.setOnClickListener {
            GoToEditProfile()
        }
    }
    fun GoToEditProfile(){
        val intent = Intent(this, editprofile::class.java)
        startActivity(intent)
    }

}