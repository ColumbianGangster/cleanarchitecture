package com.cleanarchitecture.data.repository

import com.cleanarchitecture.data.datastore.RichRelevanceRemoteDataStore
import com.cleanarchitecture.data.mappers.RichRelevanceMapper
import com.cleanarchitecture.domain.richrelevance.DomainRecommendedProduct
import com.cleanarchitecture.domain.richrelevance.RichRelevanceRepository
import io.reactivex.Single

class RichRelevanceRepositoryImpl(private val remote: RichRelevanceRemoteDataStore) : RichRelevanceRepository {

    private val richRelevanceMapper = RichRelevanceMapper()

    override fun getProducts(): Single<List<DomainRecommendedProduct>> = remote.addToCart()
            .map { richRelevanceMapper.mapList(it.placements.first().recommendedProducts) }

}

