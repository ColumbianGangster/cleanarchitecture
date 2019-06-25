package com.cleanarchitecture.domain.albums

import io.reactivex.Single

interface RichRelevanceRepository {
    fun getProducts(): Single<List<DomainRecommendedProduct>>
}