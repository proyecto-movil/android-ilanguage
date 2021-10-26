package com.example.ilanguage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.example.ilanguage.models_login.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainMenuActivity : AppCompatActivity() {

    //variables to initializate user
    lateinit var sharedPreferences : SharedPreferences
    private var userLogged : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Function added to get User
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        loadUserLogged()
            Log.e("USEEEEERRRRR", userLogged.toString())
        changeTextGreetingsName()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.profile_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemProfile)
            GoToProfile()
        if(item.itemId == R.id.itemLogOut)
            GoToLogin()
        return super.onOptionsItemSelected(item)
    }

    fun GoToProfile(){
        val intent = Intent(this, ProfileViewActivity::class.java)
        startActivity(intent)
    }
    fun GoToLogin(){
        val intent = Intent(this, login::class.java)
        startActivity(intent)
    }

    private fun loadUserLogged() {
        var json : String? = sharedPreferences.getString("userLogged",null)
        var userType: Type = object : TypeToken<User>() {}.type;
        userLogged = Gson().fromJson(json,userType)
    }

    private fun changeTextGreetingsName(){
        var text = findViewById<TextView>(R.id.textView10)
        text.text = userLogged?.name ?: "FAIL NAME"

    }
}