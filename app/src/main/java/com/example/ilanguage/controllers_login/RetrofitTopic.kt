package com.example.ilanguage.controllers_login

import com.example.ilanguage.models_login.Topic
import com.example.ilanguage.services_login.TopicService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitTopic {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://proyecto-moviles-326304.rj.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    public val service: TopicService = retrofit.create<TopicService>(TopicService::class.java)
}