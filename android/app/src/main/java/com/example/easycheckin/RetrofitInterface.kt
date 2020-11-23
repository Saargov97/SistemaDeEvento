package com.example.routemap

import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {

    @get:GET("position")
    val positions : Call<List<PostModel?>?>?

    @POST("position")
    fun addPosition(@Body body: PostModel): Call<PostModel?>?

    @PUT("position/{id}")
    fun updatePosition(@Path("id") id: Int, @Body body: PostModel): Call<PostModel?>?

    @DELETE("position/{id}")
    fun deletePosition(@Path("id") id: Int): Call<String?>?


    companion object {
        const val BASE_URL = "http://10.1.0.254:8081" // LOCALHOST
        // const val BASE_URL = "http://ec2-54-87-234-235.compute-1.amazonaws.com:3000" // AMAZON EC2
    }

}