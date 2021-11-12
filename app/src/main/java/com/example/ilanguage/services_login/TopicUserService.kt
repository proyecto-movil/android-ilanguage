package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.LanguageContent
import com.example.ilanguage.models_login.TopicContent
import com.example.ilanguage.models_login.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TopicUserService {
    @GET("api/users/{userId}/languages")
    fun getTopicsByUserId(@Path("userId") userId: Int) : Call<TopicContent> //Not sure TopicContent

    @POST("api/users/{userId}/topics/{topicId}")
    fun assignTopicUserId(@Path("userId") userId: Int, @Path("topicId") topicId: Int) : Call<User>

}