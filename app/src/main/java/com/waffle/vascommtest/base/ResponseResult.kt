package com.waffle.vascommtest.base

import javax.net.ssl.HttpsURLConnection

sealed interface ResponseResult<out T> {
    class Success<T>(val data: T) : ResponseResult<T>
    class Error<T>(val error: T) : ResponseResult<T>
}

data class ErrorResponse(val code: Int? = 0, val msg: String? = "")
data class ResponseWrapper<out T>(
    val data: T?,
    val errorResponse: ErrorResponse?
)