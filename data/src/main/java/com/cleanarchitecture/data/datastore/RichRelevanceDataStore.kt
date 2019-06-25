package com.cleanarchitecture.data.datastore

import com.cleanarchitecture.data.entities.DataAlbum
import com.cleanarchitecture.data.entities.DataProduct
import com.richrelevance.recommendations.PlacementResponseInfo
import io.reactivex.Single

interface RichRelevanceDataStore {

    fun getProducts(): Single<List<DataProduct>>
    fun addToCart(): Single<PlacementResponseInfo>
}