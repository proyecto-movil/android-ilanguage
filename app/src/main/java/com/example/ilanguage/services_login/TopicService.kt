package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.TopicContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TopicService {
    @GET("api/users/{userId}/topics")
    fun getTopicsByUser(@Path("userId") userId: Int) : Call<TopicContent>

    @GET("api/topic")
    fun getTopics() : Call<TopicContent>

}