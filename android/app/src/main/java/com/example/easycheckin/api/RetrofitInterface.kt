package com.example.easycheckin.api

import com.example.easycheckin.api.UserModel
import com.example.easycheckin.api.EventoModel
import com.example.easycheckin.api.InscricaoModel
import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {

    @GET("users")
    fun users() : Call<List<UserModel?>?>?

    @GET("eventosUsuario/{id}")
    fun events(@Path("id") id: Int) : Call<List<EventoModel?>?>?

    @GET("inscricaoEvento/{id}")
    fun inscricoes(@Path("id") id: Int) : Call<List<InscricaoModel?>?>?

    @PUT("checkin/{id}")
    fun updateCheckin(@Path("id") id: Int, @Body body: InscricaoModel): Call<InscricaoModel?>?

   /* @POST("position")
    fun addPosition(@Body body: PostModel): Call<PostModel?>?

    @PUT("position/{id}")
    fun updatePosition(@Path("id") id: Int, @Body body: PostModel): Call<PostModel?>?

    @DELETE("position/{id}")
    fun deletePosition(@Path("id") id: Int): Call<String?>?*/


    companion object {
        const val BASE_URL = "http://10.1.0.254:8081" // LOCALHOST
        // const val BASE_URL = "http://ec2-54-87-234-235.compute-1.amazonaws.com:3000" // AMAZON EC2
    }

}