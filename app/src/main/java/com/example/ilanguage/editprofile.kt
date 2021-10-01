package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class editprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        val btSave = findViewById<Button>(R.id.btsave)
        val btCancel = findViewById<Button>(R.id.btcancel)
        btSave.setOnClickListener {
            GoToProfile()
        }
        btCancel.setOnClickListener {
            GoToProfile()
        }
    }
    fun GoToProfile(){
        val intent = Intent(this, ProfileViewActivity::class.java)
        startActivity(intent)
    }
}