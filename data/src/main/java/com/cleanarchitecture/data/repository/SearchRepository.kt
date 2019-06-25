package com.cleanarchitecture.data.repository

import com.cleanarchitecture.data.datastore.SearchRemoteDataStore
import com.cleanarchitecture.data.mappers.SearchNavigationMapper
import com.cleanarchitecture.domain.searchnavigation.DomainSearchNavigation
import com.cleanarchitecture.domain.searchnavigation.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(private val remote: SearchRemoteDataStore) : SearchRepository {

    val mapper = SearchNavigationMapper()

    override fun getNavigation(): Single<DomainSearchNavigation> = remote.getNavigation()
            .map { mapper.toDomain(it) }
}

