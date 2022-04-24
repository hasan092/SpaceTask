package com.example.spacetask.app.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spacetask.app.base.BaseViewModel
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Launcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(): BaseViewModel() {

    private val _launchers: MutableLiveData<List<Launcher>> = MutableLiveData()
    val launchers: LiveData<List<Launcher>> = _launchers

    fun getLaunches() {
        viewModelScope.launch {
            LandingRepository.getLaunches(object : ResponseHandler<List<Launcher>>{
                override fun onLoading() {
                    responseHandler.onLoading()
                }

                override fun onResponse(aResult: List<Launcher>) {
                    responseHandler.onResponse(aResult)
                    _launchers.value = aResult
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