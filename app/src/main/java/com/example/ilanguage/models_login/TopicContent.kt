package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

class TopicContent (
    @SerializedName("content") var languages: List<Language>,
)