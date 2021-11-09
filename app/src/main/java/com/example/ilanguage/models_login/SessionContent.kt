package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

class SessionContent(
   @SerializedName("content") var sessions: List<Session>
)