package com.waffle.vascommtest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.waffle.mymovieapp.utils.SingleLiveEvent
import com.waffle.vascommtest.base.BaseViewModel
import com.waffle.vascommtest.base.ErrorResponse
import com.waffle.vascommtest.base.ResponseResult
import com.waffle.vascommtest.data.AppRepository
import com.waffle.vascommtest.data.request.AuthRequest
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AppRepository) : BaseViewModel() {
    private val postLoginSuccess = MutableLiveData<SingleLiveEvent<String>>()

    fun observeLoginSuccess() : LiveData<SingleLiveEvent<String>> = postLoginSuccess

    fun postLogin(authRequest: AuthRequest) {
        isLoading.postValue(SingleLiveEvent(true))
        viewModelScope.launch {
            when(val result = repository.postLogin(authRequest)) {
                is ResponseResult.Success -> {
                    postLoginSuccess.postValue(SingleLiveEvent(result.data.data) as SingleLiveEvent<String>?)
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.error.errorResponse) as SingleLiveEvent<ErrorResponse>?)
                }
            }
        }
    }
}