package com.example.spacetask.app.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacetask.data.ResponseHandler
import com.example.spacetask.data.models.Launcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(): ViewModel() {
    fun getLaunches(responseHandler: ResponseHandler<List<Launcher>>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                LandingRepository.getLaunches(responseHandler)
            }
        }
    }
}