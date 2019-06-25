package com.cleanarchitecture.domain.searchnavigation

data class DomainSearchNavigation(
        val general: DomainGeneral,
        val banners: List<DomainBanners>,
        val menus: List<DomainMenus>,
        val breadcrumbs: List<String>,
        val pagination: DomainPagination,
        val facets: List<DomainFacets>,
        val resultsets: DomainResultsets,
        val resultcount: DomainResultCount,
        val price_range: DomainPriceRange
)

data class DomainBanners(
        val top: String
)

data class DomainDefault(
        val name: String,
        val results: List<DomainResults>
)

data class DomainFacets(
        val id: String,
        val label: String,
        val values: List<DomainValues>
)

data class DomainGeneral(
        val query: String,
        val tag: String,
        val ids: String,
        val skus: String,
        val total: Int,
        val redirect: String,
        val bannerTitle: String,
        val redirect_without_analytics: String,
        val redirect_type: String,
        val page_lower: Int,
        val page_upper: Int,
        val page_total: Int,
        val index: String
)

data class DomainItems(
        val value: String,
        val label: String,
        val selected: Boolean
)

data class DomainMenus(
        val name: String,
        val label: String,
        val type: String,
        val items: List<DomainItems>
)

data class DomainPages(
        val page: Int,
        val selected: Boolean
)

data class DomainPagination(
        val name: String,
        val previous: String,
        val current: Int,
        val next: Int,
        val last: Int,
        val pages: List<DomainPages>
)

data class DomainPriceRange(
        val max: Int,
        val min: Int
)

data class DomainResultCount(
        val total: Int,
        val pagelower: Int,
        val pageupper: Int
)

data class DomainResults(
        val id: Int,
        val sku: Int,
        val title: String,
        val brand: String,
        val price: Double,
        val image: String,
        val reevoo_score: Double,
        val reevoo_count: Int,
        val discount: String,
        val short_description: String
)

data class DomainResultsets(
        val default: DomainDefault
)

data class DomainValues(
        val value: String,
        val label: String,
        val count: Int,
        val selected: Boolean
)