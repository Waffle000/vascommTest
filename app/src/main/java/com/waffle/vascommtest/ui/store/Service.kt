package com.waffle.vascommtest.ui.store

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Service (
    val title : String,
    val price : Double,
    val location : String,
    val detailLocation: String
) : Parcelable