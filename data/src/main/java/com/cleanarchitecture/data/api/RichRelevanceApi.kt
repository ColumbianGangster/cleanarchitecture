package com.cleanarchitecture.data.api

import com.cleanarchitecture.data.entities.DataAlbum
import com.cleanarchitecture.data.entities.DataProduct
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RichRelevanceApi {

    @GET("rrserver/api/rrPlatform/recsForPlacements")
    fun getProducts(@Query("apiKey") apiKey: String,
                    @Query("apiClientKey") apiClientKey: String,
                    @Query("userId") userId: String,
                    @Query("sessionId") sessionId: String,
                    @Query("placements") placements: String,
                    @Query("productId") productId: String): Single<List<DataProduct>>
}