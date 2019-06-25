package com.cleanarchitecture.data.datastore

import com.cleanarchitecture.data.api.SearchApi
import com.cleanarchitecture.data.entities.DataSearchNavigation
import io.reactivex.Single

class SearchRemoteDataStore constructor(private val api: SearchApi) : SearchDataStore {

    override fun getNavigation(): Single<DataSearchNavigation> =
            api.getNavigation()

}