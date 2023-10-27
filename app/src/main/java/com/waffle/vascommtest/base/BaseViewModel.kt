package com.waffle.vascommtest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waffle.mymovieapp.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    protected val isError = MutableLiveData<SingleLiveEvent<ErrorResponse>>()
    protected val isLoading = MutableLiveData<SingleLiveEvent<Boolean>>()

    fun observeSingleError(): LiveData<SingleLiveEvent<ErrorResponse>> = isError
    fun observeLoading(): LiveData<SingleLiveEvent<Boolean>> = isLoading
}