package com.cleanarchitecture.domain.home

import java.math.BigDecimal

data class DomainPromotedItem(val name: String,
                              val rating: Float,
                              val ratingCount: Long,
                              val price: BigDecimal,
                              val imageUrl: String,
                              val savings: String)