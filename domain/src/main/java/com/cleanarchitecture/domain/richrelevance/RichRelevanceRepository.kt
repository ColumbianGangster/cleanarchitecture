package com.cleanarchitecture.domain.richrelevance

import io.reactivex.Single

interface RichRelevanceRepository {
    fun getProducts(): Single<List<DomainRecommendedProduct>>
}