package com.example.ilanguage.controllers_login

import com.example.ilanguage.services_login.TeacherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitTeacher {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    public val service = retrofit.create<TeacherService>(TeacherService::class.java)
}