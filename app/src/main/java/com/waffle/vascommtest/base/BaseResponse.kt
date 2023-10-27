package com.waffle.vascommtest.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val `data`: T?
)