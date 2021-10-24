package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.ilanguage.controllers_login.RetrofitLanguage
import com.example.ilanguage.models_login.*
import com.example.ilanguage.services_login.LanguageService
import com.example.ilanguage.services_login.UserService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class activity_details_personal : AppCompatActivity() {
    var languageOptions: List<Language> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_personal)

        val btnBack = findViewById<ImageView>(R.id.back_image3)
        val btnContinueUser = findViewById<Button>(R.id.btn_continue_signup_users)



        var languageString = mutableListOf<String>()

        setLanguageListString(languageString)



        btnContinueUser.setOnClickListener {
            val intent = Intent(this,MainMenuActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this,details_signup::class.java)
            startActivity(intent)
        }
    }


    private fun setLanguageListString(languageString: MutableList<String>) {
        //val request = LanguageController().languageService.getLanguages()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        RetrofitLanguage.service.getLanguages().enqueue(object : Callback<LanguageContent> {
            override fun onResponse(call: Call<LanguageContent>?, response: Response<LanguageContent>?) {
                val apiResponse = response?.body()
                if (apiResponse != null) {
                    UpdateListLanguage(apiResponse.languages)
                    for (language in languageOptions)
                    {
                        languageString.add(language.name)
                    }
                    val languageAutoComplete = findViewById<AutoCompleteTextView>(R.id.languageAutoComplete)

                    val adapter = ArrayAdapter(this@activity_details_personal, R.layout.option_language, languageString)
                    languageAutoComplete.setText(adapter.getItem(0),false);
                    languageAutoComplete.setAdapter(adapter)
                }
            }
            override fun onFailure(call: Call<LanguageContent>, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }


    private fun UpdateListLanguage(languages: List<Language>) {
        languageOptions = languages

    }
}