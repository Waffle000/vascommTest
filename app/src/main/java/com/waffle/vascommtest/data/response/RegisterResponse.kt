package com.waffle.vascommtest.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RegisterResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable
