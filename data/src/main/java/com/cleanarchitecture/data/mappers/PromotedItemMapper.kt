package com.cleanarchitecture.data.mappers

import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.domain.home.DomainPromotedItem
import com.richrelevance.recommendations.RecommendedProduct
import java.math.BigDecimal

class PromotedItemMapper : Mapper<RecommendedProduct, DomainPromotedItem> {

    override fun map(from: RecommendedProduct) = DomainPromotedItem(
            name = from.name ?: "",
            rating = (from.rating / 2).toFloat(),
            ratingCount = from.numReviews,
            imageUrl = from.imageUrl ?: "",
            price = BigDecimal.valueOf(from.priceCents.toLong()),
            savings = "")

}