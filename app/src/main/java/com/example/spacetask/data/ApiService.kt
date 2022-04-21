package com.example.spacetask.data

import com.example.spacetask.data.models.Launcher
import com.example.spacetask.data.models.Rocket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("launches")
    fun getLaunches(): Call<List<Launcher>>

    @GET("rockets/{rocket_id}")
    fun getRocket(@Path("rocket_id") rocketId: String): Call<Rocket>
}