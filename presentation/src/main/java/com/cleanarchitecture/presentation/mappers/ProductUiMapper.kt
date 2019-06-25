package com.cleanarchitecture.presentation.mappers

import com.cleanarchitecture.domain.richrelevance.DomainRecommendedProduct
import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.presentation.albums.UiProduct

class ProductUiMapper : Mapper<DomainRecommendedProduct, UiProduct> {

    override fun map(from: DomainRecommendedProduct) = UiProduct(
            name = from.name

    )
}