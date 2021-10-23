package com.example.ilanguage.models_login

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("api/user")
    fun getUsers() : Call<UserContent>

    @GET("api/user/{id}")
    fun getUserById(@Path("id") id: Int) : Call<User>

    @POST("api/user")
    fun createUser(@Body requestBody: RequestBody) : Response<ResponseBody>

    @POST("api/user/{userId}/role/{roleId}")
    fun assignUserRole(@Path("userId") userId: Int,
                       @Path("roleId") roleId: Int) : Call<User>
}