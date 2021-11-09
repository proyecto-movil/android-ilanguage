package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.Session
import com.example.ilanguage.models_login.SessionContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SessionService {
    @GET("api/users/{userId}/sessions")
    fun getSessionsByUserId(@Path("userId") userId: Int ): Call<SessionContent>
}