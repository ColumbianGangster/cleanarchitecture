package com.cleanarchitecture.domain.searchnavigation

import com.cleanarchitecture.domain.common.SingleNoParamsUseCase
import com.cleanarchitecture.domain.common.SingleRxTransformer
import io.reactivex.Single

class GetProductsBySearchNavigationUseCase(transformer: SingleRxTransformer<DomainSearchNavigation>,
                                           private val repositories: SearchRepository) : SingleNoParamsUseCase<DomainSearchNavigation>(transformer) {

    override fun create(): Single<DomainSearchNavigation> =
            repositories.getNavigation()
}