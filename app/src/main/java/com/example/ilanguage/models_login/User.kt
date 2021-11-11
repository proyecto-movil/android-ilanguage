package com.example.ilanguage.models_login

import com.google.gson.annotations.SerializedName

 class User(ID: Int = 0, NAME : String = "", LASTNAME: String = "", EMAIL: String = "",  PASSWORD: String = "",

             DESCRIPTION: String = "",  MEDIA : Int = 0) {

     @SerializedName("id")
     var id: Int = ID

     @SerializedName("name")
     var name: String = NAME

     @SerializedName("lastName")
     var lastName: String = LASTNAME

     @SerializedName("email")
     var email: String = EMAIL

     @SerializedName("password")
     var password: String = PASSWORD

     @SerializedName("description")
     var description: String = DESCRIPTION


     @SerializedName("media")
     var media: Int = MEDIA
 }
