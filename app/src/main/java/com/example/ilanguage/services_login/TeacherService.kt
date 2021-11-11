package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.UserContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TeacherService {
    @GET("/api/languages/{languageId}/topics/{topicId}/tuthors")
    fun getTeachersByLanguageIdAndTopicId(@Path("languageId") languageId: Int, @Path("topicId") topicId: Int ): Call<UserContent>
}