package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.LanguageContent
import retrofit2.Call
import retrofit2.http.GET

interface LanguageService {
    @GET("api/languages")
    fun getLanguages() : Call<LanguageContent>

    //TODO: To refactor code, create a getById endpoint in backend if neccesary, if not, mobile app is going to do the job

}