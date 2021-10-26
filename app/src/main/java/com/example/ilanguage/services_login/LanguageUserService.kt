package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.LanguageContent
import com.example.ilanguage.models_login.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LanguageUserService {
    @GET("api/users/{userId}/languages")
    fun getLanguagesByUserId(@Path("userId") userId: Int) : Call<LanguageContent> //Not sure LanguageContent

    @POST("api/users/{userId}/languages/{languageId}")
    fun assignLanguageToUserId(@Path("userId") userId: Int, @Path("languageId") languageId: Int) : Call<User>

}