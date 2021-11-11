package com.example.ilanguage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.ilanguage.controllers_login.RetrofitUser
import com.example.ilanguage.models_login.User
import com.example.ilanguage.models_login.UserContent
import com.example.ilanguage.services_login.UserService
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class login_info : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences
    private var users: List<User>? = null
    private var userLogged : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_info)

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        val btnBack = findViewById<ImageView>(R.id.back_image_login_info)

        btnBack.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        signInButton()

    }
    private fun verifyLogSuccessful(email: String, password: String): User? {
        for (user in users!!) {
            if (user.email == email && user.password == password) {
                userLogged = user;
                return userLogged;
            }
        }
        return null;
    }
    private fun signInButton() {


        val btnContinue = findViewById<Button>(R.id.btn_signin3)
        btnContinue.setOnClickListener {

            val email = findViewById<TextInputEditText>(R.id.emailInputEditText)
            val password = findViewById<TextInputEditText>(R.id.passwordInputEditText)

            if (TextUtils.isEmpty(email.text.toString()) ||
                TextUtils.isEmpty(password.text.toString())
            ) {
                Toast.makeText(
                    this,
                    "Email / Password son requeridos",
                    Toast.LENGTH_LONG
                ).show();
            } else {
                RetrofitUser.service.getUsers().enqueue(object : Callback<UserContent> {
                        override fun onResponse(  call: Call<UserContent>,response: Response<UserContent>)
                        {
                            val apiResponse = response?.body()
                            if (apiResponse != null) {
                                users = apiResponse.users
                                if (verifyLogSuccessful(email.text.toString(),password.text.toString()) != null){

                                    saveDataUserLogged()
                                    val intent = Intent(applicationContext,MainMenuActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    startActivity(intent)
                                } else {

                                }
                            }
                        }

                        override fun onFailure(call: Call<UserContent>, t: Throwable) {
                            Toast.makeText(
                                this@login_info,
                                "Intentelo de nuevo, por favor",
                                Toast.LENGTH_LONG
                            ).show();

                            t?.printStackTrace()
                        }
                    })
            }
        }
    }

    private fun saveDataUserLogged() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var json : String = Gson().toJson(userLogged);
        editor.putString("userLogged",json)
        editor.apply();
    }

}