package com.example.ilanguage.services_login

import com.example.ilanguage.models_login.User
import com.example.ilanguage.models_login.UserContent
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("api/user")
    fun getUsers() : Call<UserContent>

    @GET("api/user/{id}")
    fun getUserById(@Path("id") id: Int) : Call<User>

    @POST("api/user")
    fun createUser(@Body userData: User) : Call<User>

    @POST("api/user/{userId}/role/{roleId}")
    fun assignUserRole(@Path("userId") userId: Int,
                       @Path("roleId") roleId: Int) : Call<User>
    @PUT("api/user/{id}")
    fun putuser(@Path("id") id: Int,
                @Field("name" ) name:String,
                @Field("email" ) email:String,
                @Field("password" ) password:String,
                @Field("description" ) description:String,


                ):Call<User>

}