package com.cleanarchitecture.domain.richrelevance

import com.cleanarchitecture.domain.common.SingleNoParamsUseCase
import com.cleanarchitecture.domain.common.SingleRxTransformer
import io.reactivex.Single

class GetRecommendedProductsUseCase(transformer: SingleRxTransformer<List<DomainRecommendedProduct>>,
                                    private val repository: RichRelevanceRepository) : SingleNoParamsUseCase<List<DomainRecommendedProduct>>(transformer) {

    override fun create(): Single<List<DomainRecommendedProduct>> =
            repository.getProducts()

}
