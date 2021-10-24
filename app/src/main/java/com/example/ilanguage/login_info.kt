package com.example.ilanguage

import android.content.Intent
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
    private fun verifyLogSuccessful(email: String, password: String): User? {
        for (user in users!!) {
            if (user.email == email && user.password == password) {
                return user;
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
                // METODO WTF
                RetrofitUser.service.getUsers().enqueue(object : Callback<UserContent> {
                        override fun onResponse(  call: Call<UserContent>,response: Response<UserContent>)
                        {
                            val apiResponse = response?.body()
                            if (apiResponse != null) {
                                users = apiResponse.users
                                if (verifyLogSuccessful(email.text.toString(),password.text.toString()) != null){

                                    val intent = Intent(applicationContext,MainMenuActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    startActivity(intent)
                                } else {

                                }
                            }
                        }

                        override fun onFailure(call: Call<UserContent>, t: Throwable) {
                            Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", "ON FAILUARE")

                            t?.printStackTrace()
                        }
                    })



                /*if(authenticationUser(email.text.toString(),password.text.toString()) != null) {
                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        this,
                        "Email / Password incorrectos",
                        Toast.LENGTH_LONG
                    ).show();
                }*/
            }
        }
    }

    /*private fun setListUser(userList: List<User>) {
        Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAA","ENTRO AL SET LIST USER")

        this.users = userList
        Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAA",this.users.toString())

    }

    private fun authenticationUser(email: String, password: String): User? {

        Log.e("HOLAAAAAAAA","ENTRANDO AL AUTHENTICATIONUSER")
        //TODO: LOG E IS EXECUTING FIRST THAN GETALLUSERS
        getAllUsers()//(Users)
        Log.e("ESTADO DEL USUARIO EN AUTHENTICATION",users.toString())

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
        Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAA","ENTRANDO A GET ALL USERS")

        RetrofitUser.service.getUsers()
            .enqueue(object : Callback<UserContent> {
            override fun onResponse(call: Call<UserContent>?, response: Response<UserContent>?) {
                val apiResponse = response?.body()
                if (apiResponse != null) {
                    Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAA", Gson().toJson(apiResponse))
                    setListUser(apiResponse.users)
                }
            }
            override fun onFailure(call: Call<UserContent>, t: Throwable?) {
                t?.printStackTrace()
            }
        })
        /**/

    }*/
}