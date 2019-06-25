package com.cleanarchitecture.data.datastore

import com.cleanarchitecture.data.entities.DataSearchNavigation
import io.reactivex.Single

interface SearchDataStore {
    fun getNavigation(): Single<DataSearchNavigation>
}