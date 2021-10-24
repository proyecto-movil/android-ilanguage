package com.example.ilanguage.controllers_login

import com.example.ilanguage.services_login.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUser {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    public val service: UserService = retrofit.create<UserService>(UserService::class.java)
}