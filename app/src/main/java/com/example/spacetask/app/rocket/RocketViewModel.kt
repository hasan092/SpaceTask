package com.example.spacetask.app.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spacetask.app.base.BaseViewModel
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(): BaseViewModel() {

    private val _rocket: MutableLiveData<Rocket> = MutableLiveData()
    val rocket: LiveData<Rocket> = _rocket

    fun getRocket(id:String) {
        viewModelScope.launch {
            RocketRepository.getRocket(id, object : ResponseHandler<Rocket> {
                override fun onLoading() {
                    responseHandler.onLoading()
                }

                override fun onResponse(aResult: Rocket) {
                    responseHandler.onResponse(aResult)
                    _rocket.value = aResult
                }

                override fun onError(aError: String) {
                    responseHandler.onError(aError)
                }

                override fun onFailure(aThrowable: Throwable) {
                    responseHandler.onFailure(aThrowable)
                }
            })
        }
    }
}