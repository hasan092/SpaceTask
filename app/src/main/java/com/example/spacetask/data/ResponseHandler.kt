package com.example.spacetask.data

interface ResponseHandler<T> {
    fun onLoading()
    fun onResponse(aResult: T)
    fun onError(aError: String)
    fun onFailure(aThrowable: Throwable)
}