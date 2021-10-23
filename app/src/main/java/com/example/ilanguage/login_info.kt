package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.ilanguage.models_login.User
import com.example.ilanguage.models_login.UserContent
import com.example.ilanguage.models_login.UserService
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class login_info : AppCompatActivity() {

    private var users: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_info)


        val btnBack = findViewById<ImageView>(R.id.back_image_login_info)

        btnBack.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        signInButton()

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
                if(authenticationUser(email.text.toString(),password.text.toString()) != null) {
                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        this,
                        "Email / Password incorrectos",
                        Toast.LENGTH_LONG
                    ).show();
                }
            }
        }
    }

    private fun setListUser(userList : List<User>) {
        this.users = userList
    }

    private fun authenticationUser(email: String, password: String): User? {

        getAllUsers()//(Users)

        if(users != null){
            for (user in users!!) {
                if (user.email == email && user.password == password)
                    return user
            }
        }
        else {
            Toast.makeText(
                this,
                "Ocurrio un error al obtener usuarios",
                Toast.LENGTH_LONG
            ).show();
        }

        return null;
    }

    private fun getAllUsers() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: UserService = retrofit.create<UserService>(UserService::class.java)

        val request =  service.getUsers()
        request.enqueue(object : Callback<UserContent> {
            override fun onResponse(call: Call<UserContent>?, response: Response<UserContent>?) {


                val apiResponse = response?.body()
                if (apiResponse != null) {
                    setListUser(apiResponse.users)
                }
            }
            override fun onFailure(call: Call<UserContent>, t: Throwable?) {
                t?.printStackTrace()
            }
        })
        /**/

    }
}