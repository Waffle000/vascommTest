package com.waffle.vascommtest.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class UserResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("support")
	val support: Support? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("pantone_value")
	val pantoneValue: String? = null
) : Parcelable

@Parcelize
data class Support(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable
