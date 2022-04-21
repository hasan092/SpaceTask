package com.example.spacetask.app.rocket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(): ViewModel() {
    fun getRocket(id:String, responseHandler: ResponseHandler<Rocket>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                RocketRepository.getRocket(id, responseHandler)
            }
        }
    }
}