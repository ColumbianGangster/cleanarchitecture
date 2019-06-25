package com.cleanarchitecture.domain.searchnavigation


import io.reactivex.Single

interface SearchRepository {
    fun getNavigation(): Single<DomainSearchNavigation>
}