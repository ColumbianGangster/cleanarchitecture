package com.cleanarchitecture.data.mappers

import com.cleanarchitecture.domain.albums.DomainCategory
import com.cleanarchitecture.domain.albums.DomainRange
import com.cleanarchitecture.domain.albums.DomainRecommendedProduct
import com.cleanarchitecture.domain.common.Mapper
import com.richrelevance.Range
import com.richrelevance.recommendations.Category
import com.richrelevance.recommendations.RecommendedProduct

class RichRelevanceMapper : Mapper<RecommendedProduct, DomainRecommendedProduct> {
    override fun map(from: RecommendedProduct) = DomainRecommendedProduct(
            id = from.id,
            name = from.name,
            brand = from.brand,
            genre = from.genre,
            rating = from.rating,
            numReviews = from.numReviews,
            regionalProductSku = from.regionalProductSku,
            categoryIds = from.categoryIds,
            imageUrl = from.imageUrl,
            isRecommendable = from.isRecommendable,
            priceCents = from.priceCents,
            priceRangeCents = toDomainRange(from.priceRangeCents),
            regionPriceDescription = from.regionPriceDescription,
            categories = from.categories.map { toDomainCategory(it) }
    )

    fun toDomainRange(from: Range): DomainRange = DomainRange(
            min = from.min,
            max = from.max
    )

    fun toDomainCategory(from: Category): DomainCategory = DomainCategory(
            id = from.id.toIntOrZero(),
            name = from.name,
            hasChildren = from.hasChildren()
    )
}
