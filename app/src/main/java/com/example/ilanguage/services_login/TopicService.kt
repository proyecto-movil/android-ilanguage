package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.TopicContent
import retrofit2.Call
import retrofit2.http.GET

interface TopicService {
    @GET("api/topic")
    fun getLanguages() : Call<TopicContent>
}