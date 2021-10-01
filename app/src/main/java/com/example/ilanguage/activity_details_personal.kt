package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext

class activity_details_personal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_personal)

        val btnBack = findViewById<ImageView>(R.id.back_image3)
        val btnContinueUser = findViewById<Button>(R.id.btn_continue_signup_users)

        val languageAutoComplete = findViewById<AutoCompleteTextView>(R.id.languageAutoComplete)



        val languageOptions = listOf("English","Español","Frances","Aleman")

        val adapter = ArrayAdapter(this, R.layout.option_language, languageOptions)
        languageAutoComplete.setText(adapter.getItem(0),false);
        languageAutoComplete.setAdapter(adapter)

        btnContinueUser.setOnClickListener {
            val intent = Intent(this,MainMenuActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this,details_signup::class.java)
            startActivity(intent)
        }
    }
}