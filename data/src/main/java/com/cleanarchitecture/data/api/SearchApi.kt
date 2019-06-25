package com.cleanarchitecture.data.api

import com.cleanarchitecture.data.entities.DataSearchNavigation
import io.reactivex.Single
import retrofit2.http.GET

interface SearchApi {

    @GET("navigation")
    fun getNavigation(): Single<DataSearchNavigation>

}