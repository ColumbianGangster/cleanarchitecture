package com.cleanarchitecture.presentation.albums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiProduct(
       var name: String? = null
) : Parcelable


