package com.example.spacetask.app.landing

import com.example.spacetask.data.NetworkService
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Launcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LandingRepository {

    suspend fun getLaunches(responseHandler: ResponseHandler<List<Launcher>>) {
        responseHandler.onLoading()
        NetworkService.apiService.getLaunches().enqueue(object : Callback<List<Launcher>> {
            override fun onResponse(call: Call<List<Launcher>>, response: Response<List<Launcher>>) {
                if (response.isSuccessful) {
                    responseHandler.onResponse(response.body()!!)
                } else {
                    responseHandler.onError(response.message())
                }
            }

            override fun onFailure(call: Call<List<Launcher>>, t: Throwable) {
                responseHandler.onFailure(t)
            }
        })
    }

}