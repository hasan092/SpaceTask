package com.example.spacetask.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.APIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel(), ResponseHandler<Any> {
    private val viewModelJob = SupervisorJob()
    private val _apiStatus: MutableLiveData<APIStatus> = MutableLiveData()
    val apiStatus: LiveData<APIStatus> = _apiStatus
    var responseHandler: ResponseHandler<Any> = this

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    override fun onLoading() {
        _apiStatus.value = APIStatus.loading()
    }

    override fun onResponse(aResult: Any) {
        _apiStatus.value = APIStatus.success()
    }

    override fun onError(aError: String) {
        _apiStatus.value = APIStatus.failed(aError)
    }

    override fun onFailure(aThrowable: Throwable) {
        _apiStatus.value = APIStatus.failed(aThrowable.message)
    }
}