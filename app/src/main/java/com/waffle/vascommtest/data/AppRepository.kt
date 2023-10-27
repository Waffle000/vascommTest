package com.waffle.vascommtest.data

import com.waffle.vascommtest.api.ApiService
import com.waffle.vascommtest.base.BaseRemote
import com.waffle.vascommtest.base.BaseResponse
import com.waffle.vascommtest.base.ResponseResult
import com.waffle.vascommtest.base.ResponseWrapper
import com.waffle.vascommtest.data.request.AuthRequest
import org.json.JSONObject
import retrofit2.Response

class AppRepository(private val apiService: ApiService) : BaseRemote() {
    private suspend fun <T> getValue(
        request: suspend () -> Response<T>
    ): ResponseResult<ResponseWrapper<T>> {
        return try {
            val res = request()
            val body = res.body()
            if (res.isSuccessful || res.code() in 200..202) {
                if (body != null) {
                    return ResponseResult.Success(
                        ResponseWrapper(
                            body,
                            null
                        )
                    )
                }
            }
            return errorState(res.code(),
                JSONObject(res.errorBody()?.string().toString() ).getString("error"))

        } catch (e: Exception) {
            errorState(
                msg = e.message ?: "Terjadi Kesalahan")
        }
    }

    suspend fun postRegister(authRequest: AuthRequest) = suspendDataResult {
        getValue {
            apiService.postRegister(authRequest)
        }
    }

    suspend fun postLogin(authRequest: AuthRequest) = suspendDataResult {
       getValue {
           apiService.postLogin(authRequest)
       }
    }

    suspend fun getUserData(id: Int) = suspendDataResult {
        getValue {
            apiService.getUserData(id)
        }
    }
}