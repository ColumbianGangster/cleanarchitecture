package com.cleanarchitecture.presentation.mappers

import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.domain.searchnavigation.DomainSearchNavigation
import com.cleanarchitecture.presentation.search.UiSearchNavigation


class SearchNavigationUiMapper : Mapper<DomainSearchNavigation, UiSearchNavigation> {
    override fun map(from: DomainSearchNavigation) = UiSearchNavigation(
            userId = from.banners.first().top,
            description = from.menus.first().name,
            url = from.facets.first().id
    )
}