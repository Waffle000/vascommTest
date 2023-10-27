package com.waffle.vascommtest.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.waffle.mymovieapp.utils.SingleLiveEvent
import com.waffle.vascommtest.base.BaseViewModel
import com.waffle.vascommtest.base.ErrorResponse
import com.waffle.vascommtest.base.ResponseResult
import com.waffle.vascommtest.data.AppRepository
import com.waffle.vascommtest.data.response.UserResponse
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: AppRepository) : BaseViewModel() {
    private val getUserDataSuccess = MutableLiveData<SingleLiveEvent<UserResponse>>()

    fun observeUserDataSuccess() : LiveData<SingleLiveEvent<UserResponse>> = getUserDataSuccess

    fun getUserData(id: Int) {
        isLoading.postValue(SingleLiveEvent(true))
        viewModelScope.launch {
            when(val result = repository.getUserData(id)) {
                is ResponseResult.Success -> {
                    getUserDataSuccess.postValue(SingleLiveEvent(result.data.data) as SingleLiveEvent<UserResponse>?)
                }
                is ResponseResult.Error -> {
                    isError.postValue(SingleLiveEvent(result.error.errorResponse) as SingleLiveEvent<ErrorResponse>?)
                }
            }
        }
    }
}