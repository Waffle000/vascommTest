package com.waffle.vascommtest.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffle.mymovieapp.utils.SingleLiveEvent
import com.waffle.vascommtest.base.BaseViewModel
import com.waffle.vascommtest.base.ErrorResponse
import com.waffle.vascommtest.base.ResponseResult
import com.waffle.vascommtest.data.AppRepository
import com.waffle.vascommtest.data.request.AuthRequest
import com.waffle.vascommtest.data.response.RegisterResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AppRepository) : BaseViewModel() {
    private val postRegisterSuccess = MutableLiveData<SingleLiveEvent<RegisterResponse>>()

    fun observeRegisterSuccess() : LiveData<SingleLiveEvent<RegisterResponse>> = postRegisterSuccess

    fun postRegister(authRequest: AuthRequest) {
        isLoading.postValue(SingleLiveEvent(true))
        viewModelScope.launch {
            when(val result = repository.postRegister(authRequest)) {
                is ResponseResult.Success -> {
                    postRegisterSuccess.postValue(SingleLiveEvent(result.data.data) as SingleLiveEvent<RegisterResponse>?)
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.error.errorResponse) as SingleLiveEvent<ErrorResponse>?)
                }
            }
        }
    }
}