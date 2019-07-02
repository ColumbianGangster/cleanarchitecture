package com.cleanarchitecture.presentation.products

data class UiProducts(
        val products: List<UiProduct>? = null
)

data class UiProduct(
        val id: String? = null,
        val sku: String? = null,
        val label: String? = null,
        val link: String? = null,
        val fullDescription: String? = null,
        val brand: UiBrand? = null,
        val boxType: String? = null,
        val isOnline: Boolean? = null,
        val subProducts: Any? = null,
        val images: List<UiImage>? = null,
        val mainFeatures: List<String>? = null,
        val categorisation: UiCategorisation? = null,
        val externalCategorisation: UiExternalCategorisation? = null,
        val price: UiPrice? = null,
        val wasPrice: UiWasPrice? = null,
        val priceInBundle: UiPriceInBundle? = null,
        val preOrder: UiPreOrder? = null,
        val forwardOrder: UiForwardOrder? = null,
        val deliveryOptions: List<UiDeliveryOption>? = null,
        val energyEfficiency: Any? = null,
        val icons: List<Any>? = null,
        val badges: List<UiBadge>? = null,
        val customerReview: UiCustomerReview? = null
)

data class UiBrand(
        val id: String? = null,
        val label: String? = null
)

data class UiCategorisation(
        val universeId: String? = null,
        val categoryId: String? = null,
        val marketId: String? = null,
        val segmentId: String? = null
)

data class UiCustomerReview(
        val number: Int? = null,
        val averageScore: Double? = null
)

data class UiBadge(
        val name: String? = null,
        val link: String? = null,
        val imageUrl : String? = null
)

data class UiDeliveryOption(
        val id: String? = null,
        val label: String? = null,
        val enabled: Boolean? = null
)

data class UiExternalCategorisation(
        val planningGroup: UiPlanningGroup? = null,
        val subPlanningGroup: UiSubPlanningGroup? = null,
        val merchandiseArea: UiMerchandiseArea? = null
)

data class UiForwardOrder(
        val available: Boolean? = null,
        val message: Any? = null
)

data class UiImage(
        val url: String? = null,
        val urlSizeMedium: String? = null
)

data class UiMerchandiseArea(
        val id: String? = null,
        val label: String? = null
)

data class UiPlanningGroup(
        val id: String? = null,
        val label: String? = null
)

data class UiPreOrder(
        val available: Boolean? = null,
        val message: Any? = null
)

data class UiPrice(
        val amount: Int? = null,
        val vatAmount: Int? = null,
        val currencyCode: String? = null,
        val discountAmount: Int? = null
)

data class UiPriceInBundle(
        val amount: Int? = null,
        val vatAmount: Int? = null,
        val currencyCode: String? = null
)

data class UiSubPlanningGroup(
        val id: String? = null,
        val label: String? = null
)

data class UiWasPrice(
        val amount: Int? = null,
        val vatAmount: Int? = null,
        val currencyCode: String? = null,
        val dateFrom: String? = null,
        val dateTo: String? = null,
        val discountAmount: String? = null
)
