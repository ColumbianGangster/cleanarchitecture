package com.cleanarchitecture.data.mappers

import com.cleanarchitecture.data.entities.*
import com.cleanarchitecture.domain.searchnavigation.*

class SearchNavigationMapper {

    fun toDomain(data: DataSearchNavigation): DomainSearchNavigation = DomainSearchNavigation(
            general = toDomainGeneral(data.general),
            banners = data.banners.map { toDomainBanners(it) },
            menus = data.menus.map { toDomainMenus(it) },
            breadcrumbs = data.breadcrumbs,
            pagination = toDomainPagination(data.pagination),
            facets = data.facets.map { toDomainFacets(it) },
            resultsets = toDomainResultSets(data.resultsets),
            resultcount = toDomainResultCount(data.resultcount),
            price_range = toDomainPriceRange(data.price_range)
    )

    private fun toDomainPriceRange(data: DataPriceRange): DomainPriceRange = DomainPriceRange(
            max = data.max.toIntOrZero(),
            min = data.min.toIntOrZero()
    )

    private fun toDomainResultCount(data: DataResultCount): DomainResultCount = DomainResultCount(
            total = data.total.toIntOrZero(),
            pagelower = data.pagelower.toIntOrZero(),
            pageupper = data.pageupper.toIntOrZero()
    )

    private fun toDomainResultSets(data: DataResultsets): DomainResultsets = DomainResultsets(
            default = toDomainDefault(data.default)
    )

    private fun toDomainDefault(data: DataDefault): DomainDefault = DomainDefault(
            name = data.name,
            results = data.results.map { toDomainResults(it) }
    )

    private fun toDomainResults(data: DataResults): DomainResults = DomainResults(
            id = data.id.toIntOrZero(),
            sku = data.sku.toIntOrZero(),
            title = data.title,
            brand = data.brand,
            price = data.price.toDoubleOrZero(),
            image = data.image,
            reevoo_score = data.reevoo_score.toDoubleOrZero(),
            reevoo_count = data.reevoo_count.toIntOrZero(),
            discount = data.discount,
            short_description = data.short_description

    )

    private fun toDomainFacets(data: DataFacets): DomainFacets = DomainFacets(
            id = data.id,
            label = data.label,
            values = data.values.map { toDomainValues(it) }
    )

    private fun toDomainValues(data: DataValues): DomainValues = DomainValues(
            value = data.value,
            label = data.label,
            count = data.count.toIntOrZero(),
            selected = data.selected
    )

    private fun toDomainPagination(data: DataPagination): DomainPagination = DomainPagination(
            name = data.name,
            previous = data.previous,
            current = data.current.toIntOrZero(),
            next = data.next.toIntOrZero(),
            last = data.last.toIntOrZero(),
            pages = data.pages.map { toDomainPages(it) }

    )

    private fun toDomainPages(data: DataPages): DomainPages = DomainPages(
            page = data.page.toIntOrZero(),
            selected = data.selected
    )

    fun toDomainGeneral(data: DataGeneral): DomainGeneral = DomainGeneral(
            query = data.query,
            tag = data.tag,
            ids = data.ids,
            skus = data.skus,
            total = data.total.toIntOrZero(),
            redirect = data.redirect,
            bannerTitle = data.bannerTitle,
            redirect_without_analytics = data.redirect_without_analytics,
            redirect_type = data.redirect_type,
            page_lower = data.page_lower.toIntOrZero(),
            page_upper = data.page_upper.toIntOrZero(),
            page_total = data.page_total.toIntOrZero(),
            index = data.index
    )

    fun toDomainBanners(data: DataBanners): DomainBanners = DomainBanners(data.top)

    fun toDomainMenus(data: DataMenus): DomainMenus = DomainMenus(
            name = data.name,
            label = data.label,
            type = data.type,
            items = data.items.map { toDomainItems(it) }
    )

    fun toDomainItems(data: DataItems): DomainItems = DomainItems(
            value = data.value,
            label = data.label,
            selected = data.selected
    )
}


fun String.toIntOrZero() = toIntOrNull() ?: 0
fun String.toDoubleOrZero() = toDoubleOrNull() ?: 0.0