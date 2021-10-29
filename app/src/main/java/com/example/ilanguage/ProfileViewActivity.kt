package com.example.ilanguage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.ilanguage.controllers_login.RetrofitLanguage
import com.example.ilanguage.controllers_login.RetrofitLanguageUser
import com.example.ilanguage.models_login.Language
import com.example.ilanguage.models_login.LanguageContent
import com.example.ilanguage.models_login.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response
import java.lang.reflect.Type
import javax.security.auth.callback.Callback

class ProfileViewActivity : AppCompatActivity() {

    //variables to initializate user
    lateinit var sharedPreferences : SharedPreferences
    private var userLogged : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)

        //Function added to get User
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        loadUserLogged()

        changeUserValue()

        val btEditProfile = findViewById<Button>(R.id.btEditProfile)
        btEditProfile.setOnClickListener {
            GoToEditProfile()
        }


    }
    fun GoToEditProfile(){
        val intent = Intent(this, editprofile::class.java)
        startActivity(intent)
    }
    private fun loadUserLogged() {
        var json : String? = sharedPreferences.getString("userLogged",null)
        var userType: Type = object : TypeToken<User>() {}.type;
        userLogged = Gson().fromJson(json,userType)
    }

    private fun  changeUserValue() {
        val userName = findViewById<TextView>(R.id.tvProfileName)
        val sb = StringBuilder()
        userName.text = sb.append(userLogged?.name," ",userLogged?.lastName)

        val userDescription= findViewById<TextView>(R.id.tvDescription)
        userDescription.text = userLogged?.description ?: "Error"

        val userLanguages = findViewById<TextView>(R.id.tvLanguage)
        userLanguages.text = ""


        RetrofitLanguageUser.service.getLanguagesByUserId(userLogged!!.id).enqueue(object : retrofit2.Callback<LanguageContent>
        {
            override fun onResponse(call: Call<LanguageContent>, response: Response<LanguageContent>)  {
                val apiResponse = response?.body()
                if (apiResponse != null) {
                    for (language in apiResponse.languages){
                        userLanguages.text = language.name
                    }
                }

            }

            override fun onFailure(call: Call<LanguageContent>, t: Throwable) {
                t?.printStackTrace()

            }

        })

    }



}