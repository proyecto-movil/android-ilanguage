package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

class Topic(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
    )