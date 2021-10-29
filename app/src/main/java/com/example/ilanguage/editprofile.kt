package com.example.ilanguage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.ilanguage.models_login.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class editprofile : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    private var userLogged : User? = null
    lateinit var tvname: TextView
    lateinit var tvemail :TextView
    lateinit var tvpassword:TextView
    lateinit var tvdescripcion:TextView

    override fun onCreate(savedInstanceState: Bundle?) {


        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        loadUserLogged()
        Log.e("USEEEEERRRRR", userLogged.toString())


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        tvname=findViewById(R.id.nameImputEditText)
        tvemail=findViewById(R.id.emailImputEditText)
        tvpassword=findViewById(R.id.passwordImputEditText)
        tvdescripcion=findViewById(R.id.descripcionImputEditText)

        val btSave = findViewById<Button>(R.id.btsave)
        val btCancel = findViewById<Button>(R.id.btcancel)

        initFields(this)

        btSave.setOnClickListener {

            saveDataUserLogged()
            GoToProfile()
        }
        btCancel.setOnClickListener {
            GoToProfile()
        }
    }
    private fun initFields(context: Context) {

        tvname.text = userLogged?.name
        tvemail.text=userLogged?.email
        tvpassword.text=userLogged?.password
        tvdescripcion.text=userLogged?.description

    }

    private fun loadUserLogged() {
        var json : String? = sharedPreferences.getString("userLogged",null)
        var userType: Type = object : TypeToken<User>() {}.type;
        userLogged = Gson().fromJson(json,userType)
    }

    private fun saveDataUserLogged() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var json : String = Gson().toJson(userLogged);

        editor.putString("userLogged",json)
        editor.apply();
    }




    fun GoToProfile(){
        val intent = Intent(this, ProfileViewActivity::class.java)
        loadUserLogged()
        startActivity(intent)
    }
}