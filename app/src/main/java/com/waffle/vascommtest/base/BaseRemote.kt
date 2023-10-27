package com.waffle.vascommtest.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.net.ssl.HttpsURLConnection

abstract class BaseRemote {
    fun <T> errorState(
        errorCode: Int? = HttpsURLConnection.HTTP_INTERNAL_ERROR,
        msg: String
    ): ResponseResult<ResponseWrapper<T>> {
        return ResponseResult.Error(ResponseWrapper(null, ErrorResponse(errorCode, msg)))
    }

    suspend fun <T> suspendDataResult(request: suspend () -> ResponseResult<T>): ResponseResult<T> {
        return withContext(Dispatchers.IO) {
            request.invoke()
        }
    }
}