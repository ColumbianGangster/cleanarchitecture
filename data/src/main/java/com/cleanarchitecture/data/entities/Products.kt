package com.cleanarchitecture.data.entities

import com.google.gson.annotations.SerializedName

data class DataProduct(
    @SerializedName("name") val name: String? = null
)