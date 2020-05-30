package com.cleanarchitecture.presentation.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiPromotedItem(val name: String,
                          val rating: Float,
                          val ratingCount: String,
                          val price: String,
                          val imageUrl: String) : Parcelable