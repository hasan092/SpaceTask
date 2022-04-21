package com.example.spacetask.app.rocket

import com.example.spacetask.data.NetworkService
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Rocket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RocketRepository {

    suspend fun getRocket(id: String, responseHandler: ResponseHandler<Rocket>) {
        responseHandler.onLoading()
        NetworkService.apiService.getRocket(id).enqueue(object : Callback<Rocket> {
            override fun onResponse(call: Call<Rocket>, response: Response<Rocket>) {
                if (response.isSuccessful) {
                    responseHandler.onResponse(response.body()!!)
                } else {
                    responseHandler.onError(response.message())
                }
            }

            override fun onFailure(call: Call<Rocket>, t: Throwable) {
                responseHandler.onFailure(t)
            }
        })
    }

}