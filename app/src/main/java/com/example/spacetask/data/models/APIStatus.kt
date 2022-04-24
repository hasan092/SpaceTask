package com.example.spacetask.data.models

class APIStatus(val status: Status, private val message: String? = null) {

    enum class Status {
        SUCCESS, FAILED, LOADING
    }

    fun getApiStatus(): Status {
        return status
    }

    companion object {
        fun success(): APIStatus {
            return APIStatus(status = Status.SUCCESS)
        }

        fun failed(msg: String?): APIStatus {
            return APIStatus(status = Status.FAILED, message = msg)
        }

        fun loading(): APIStatus {
            return APIStatus(status = Status.LOADING)
        }
    }
}