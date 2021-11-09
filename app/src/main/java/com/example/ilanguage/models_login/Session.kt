package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName
import java.util.*

class Session (
   @SerializedName("id")
   var id: Int,
   @SerializedName("startAt")
   var startAt: Date,
   @SerializedName("endAt")
   var endAt: Date,
   @SerializedName("link")
   var link: String,
   @SerializedName("state")
   var state: String,
   @SerializedName("topic")
   var topic: String,
   @SerializedName("information")
   var information: String
   )
