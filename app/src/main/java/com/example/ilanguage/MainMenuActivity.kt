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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ilanguage.adapters.SessionAdapter
import com.example.ilanguage.controllers_login.RetrofitSession
import com.example.ilanguage.models_login.Session
import com.example.ilanguage.models_login.SessionContent
import com.example.ilanguage.models_login.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MainMenuActivity : AppCompatActivity() {

    //variables to initializate user
    lateinit var sharedPreferences : SharedPreferences
    lateinit var sessions: List<Session>
    lateinit var sessionAdapter: SessionAdapter
    private var userLogged : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Function added to get User
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        loadUserLogged()
            Log.e("USEEEEERRRRR", userLogged.toString())
        //changeTextGreetingsName()
        val rvSession = findViewById<RecyclerView>(R.id.rvSessions)
        loadSession(rvSession)
    }

    private fun loadSession(rvSessions: RecyclerView) {
        val request = RetrofitSession.service.getSessionsByUserId(userLogged?.id?:1)
        request.enqueue(object : Callback<SessionContent> {
            override fun onResponse(call: Call<SessionContent>, response: Response<SessionContent>) {
                sessions = response.body()!!.sessions
                sessionAdapter = SessionAdapter(sessions)
                rvSessions.adapter = sessionAdapter
                rvSessions.layoutManager = LinearLayoutManager(this@MainMenuActivity)
            }

            override fun onFailure(call: Call<SessionContent>, t: Throwable) {
                Log.d("Error de call", t.toString())
            }

        })
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
        var text = findViewById<TextView>(R.id.tvTopicLanguage)
        text.text = userLogged?.name ?: "FAIL NAME"

    }
}