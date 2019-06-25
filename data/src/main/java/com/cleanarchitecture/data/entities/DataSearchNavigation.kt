package com.cleanarchitecture.data.entities

import com.google.gson.annotations.SerializedName


data class DataSearchNavigation(
        @SerializedName("general") val general: DataGeneral,
        @SerializedName("banners") val banners: List<DataBanners>,
        @SerializedName("menus") val menus: List<DataMenus>,
        @SerializedName("breadcrumbs") val breadcrumbs: List<String>,
        @SerializedName("pagination") val pagination: DataPagination,
        @SerializedName("facets") val facets: List<DataFacets>,
        @SerializedName("resultsets") val resultsets: DataResultsets,
        @SerializedName("resultcount") val resultcount: DataResultCount,
        @SerializedName("price_range") val price_range: DataPriceRange
)

data class DataBanners(
        @SerializedName("top") val top: String
)

data class DataDefault(
        @SerializedName("name") val name: String,
        @SerializedName("results") val results: List<DataResults>
)

data class DataFacets(
        @SerializedName("id") val id: String,
        @SerializedName("label") val label: String,
        @SerializedName("values") val values: List<DataValues>
)

data class DataGeneral(
        @SerializedName("query") val query: String,
        @SerializedName("tag") val tag: String,
        @SerializedName("ids") val ids: String,
        @SerializedName("skus") val skus: String,
        @SerializedName("total") val total: String,
        @SerializedName("redirect") val redirect: String,
        @SerializedName("bannerTitle") val bannerTitle: String,
        @SerializedName("redirect_without_analytics") val redirect_without_analytics: String,
        @SerializedName("redirect_type") val redirect_type: String,
        @SerializedName("page_lower") val page_lower: String,
        @SerializedName("page_upper") val page_upper: String,
        @SerializedName("page_total") val page_total: String,
        @SerializedName("index") val index: String
)

data class DataItems(
        @SerializedName("value") val value: String,
        @SerializedName("label") val label: String,
        @SerializedName("selected") val selected: Boolean
)

data class DataMenus(
        @SerializedName("name") val name: String,
        @SerializedName("label") val label: String,
        @SerializedName("type") val type: String,
        @SerializedName("items") val items: List<DataItems>
)

data class DataPages(
        @SerializedName("page") val page: String,
        @SerializedName("selected") val selected: Boolean
)

data class DataPagination(
        @SerializedName("name") val name: String,
        @SerializedName("previous") val previous: String,
        @SerializedName("current") val current: String,
        @SerializedName("next") val next: String,
        @SerializedName("last") val last: String,
        @SerializedName("pages") val pages: List<DataPages>
)

data class DataPriceRange(
        @SerializedName("max") val max: String,
        @SerializedName("min") val min: String
)

data class DataResultCount(
        @SerializedName("total") val total: String,
        @SerializedName("pagelower") val pagelower: String,
        @SerializedName("pageupper") val pageupper: String
)

data class DataResults(
        @SerializedName("id") val id: String,
        @SerializedName("sku") val sku: String,
        @SerializedName("title") val title: String,
        @SerializedName("brand") val brand: String,
        @SerializedName("price") val price: String,
        @SerializedName("image") val image: String,
        @SerializedName("reevoo_score") val reevoo_score: String,
        @SerializedName("reevoo_count") val reevoo_count: String,
        @SerializedName("discount") val discount: String,
        @SerializedName("short_description") val short_description: String
)

data class DataResultsets(
        @SerializedName("default") val default: DataDefault
)

data class DataValues(
        @SerializedName("value") val value: String,
        @SerializedName("label") val label: String,
        @SerializedName("count") val count: String,
        @SerializedName("selected") val selected: Boolean
)