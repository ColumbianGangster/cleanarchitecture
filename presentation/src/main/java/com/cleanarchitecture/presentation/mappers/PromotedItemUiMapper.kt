package com.cleanarchitecture.presentation.mappers

import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.domain.home.DomainPromotedItem
import com.cleanarchitecture.presentation.home.UiPromotedItem

class PromotedItemUiMapper : Mapper<DomainPromotedItem, UiPromotedItem> {
    override fun map(from: DomainPromotedItem) = UiPromotedItem(
            name = from.name,
            rating = from.rating,
            ratingCount = from.ratingCount.toString(),
            price = String.format("%.2f", from.price.toFloat()/100),
            imageUrl = from.imageUrl
    )
}