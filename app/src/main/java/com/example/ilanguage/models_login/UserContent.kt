package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

data class UserContent (
    @SerializedName("content") var users: List<User>,
    @SerializedName("totalElements") var counts: Int
    )