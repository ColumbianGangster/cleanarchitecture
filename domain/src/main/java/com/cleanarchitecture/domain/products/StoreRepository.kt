package com.cleanarchitecture.domain.albums

import com.cleanarchitecture.domain.searchnavigation.DomainResults
import io.reactivex.Single

/**
 * The interface of repositories is defined in domain layer, for its' implementations look
 * in data layer
 */
interface StoreRepository {
    fun getProducts(): Single<List<DomainResults>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}