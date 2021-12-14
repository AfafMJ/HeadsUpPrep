package com.afaf.headsupprep

import retrofit2.Call
import retrofit2.http.*


interface APIInterface {

    @GET("/celebrities/")
    fun getUser(): Call<Celeb>

    @POST("/celebrities/")
    fun addUser(@Body data: CelebItem): Call<CelebItem>
    @PUT("/celebrities/{id}")
    fun updateUser(@Path("id") id: Int, @Body data: CelebItem): Call<CelebItem>

    @DELETE("/celebrities/{id}")
    fun deleteUser(@Path("id")id: Int ): Call<Void>
    abstract fun updateUser(id: CelebItem): Call<CelebItem>
}