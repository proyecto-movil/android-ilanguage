package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName


data class User (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("media")
    var media: Int
    )