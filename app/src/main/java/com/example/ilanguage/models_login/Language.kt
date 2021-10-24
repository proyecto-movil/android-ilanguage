package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

data class Language (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
    )