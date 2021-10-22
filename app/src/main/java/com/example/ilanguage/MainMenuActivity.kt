package com.example.ilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
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
}