package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

data class LanguageContent (
    @SerializedName("content") var languages: List<Language>,
    @SerializedName("totalElements") var counts: Int
)