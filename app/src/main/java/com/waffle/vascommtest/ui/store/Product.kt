package com.waffle.vascommtest.ui.store

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val star : Int,
    val title: String,
    val price: Double
): Parcelable