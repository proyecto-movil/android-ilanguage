package com.example.ilanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.ilanguage.controllers_login.RetrofitLanguage
import com.example.ilanguage.controllers_login.RetrofitLanguageUser
import com.example.ilanguage.controllers_login.RetrofitTeacher
import com.example.ilanguage.models_login.Language
import com.example.ilanguage.models_login.LanguageContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class profile_teacher : AppCompatActivity() {
    var languageOptions: List<Language> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teacher)
        val tvTeacherName = findViewById<TextView>(R.id.tvProfileTeacherName)
        val tvTeacherDescription = findViewById<TextView>(R.id.tvProfileDescriptionTeacher)

        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("nameTeacher")
        val description = bundle.getString("descriptionTeacher")
        val id = bundle.getInt("id")
        getTeacherLanguages(id)
        tvTeacherName.text = name
        tvTeacherDescription.text = description
    }

    private fun getTeacherLanguages(teacherId: Int) {
        val tvTeacherLanguages = findViewById<TextView>(R.id.tvProfileLanguageTeacher)
        var languageString = " "
        val request = RetrofitLanguageUser.service.getLanguagesByUserId(teacherId)
        request.enqueue(object : Callback<LanguageContent> {
            override fun onResponse(
                call: Call<LanguageContent>,
                response: Response<LanguageContent>
            ) {
                languageOptions = response.body()!!.languages
                if(languageOptions.size > 0){
                   for (language in languageOptions){
                       languageString += language.name + " - "
                   }
                    tvTeacherLanguages.text = languageString
                } else{
                    Log.e("No hay languages para el usuario de id: $teacherId", "No tiene lenguajes asignados")
                }
            }
            override fun onFailure(call: Call<LanguageContent>, t: Throwable) {
                Log.d("Error al obtener lenguages de el teacher de id : $teacherId", t.toString())
            }

        })
    }
}