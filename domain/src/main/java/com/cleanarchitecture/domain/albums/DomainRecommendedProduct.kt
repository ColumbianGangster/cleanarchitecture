package com.cleanarchitecture.domain.albums

data class DomainRecommendedProduct(
        var id: String? = null,
        var name: String? = null,
        var brand: String? = null,
        var genre: String? = null,
        var rating: Double = 0.toDouble(),
        var numReviews: Long = 0,
        var regionalProductSku: String? = null,
        var categoryIds: List<String>? = null,
        var imageUrl: String? = null,
        var isRecommendable: Boolean = false,
        var priceCents: Int = 0,
        var priceRangeCents: DomainRange? = null,
        var regionPriceDescription: String? = null,
        var categories: List<DomainCategory>? = null
)

data class DomainRange(
        var min: Int,
        var max: Int
)

data class DomainCategory(
        val name: String,
        val id: Int,
        val hasChildren: Boolean
)