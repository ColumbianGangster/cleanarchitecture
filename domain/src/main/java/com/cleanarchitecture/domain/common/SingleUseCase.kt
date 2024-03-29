package com.cleanarchitecture.domain.common

import io.reactivex.Single

/**
 * This class serves as an abstract use case to create or transform a Single stream
 * @param T - A generic object of type T. For example - SingleNoParamsUseCase<String> would lead
 * to creation of Single<String> once create() is called
 */

abstract class SingleUseCase<in Params, T>(private val transformer: SingleRxTransformer<T>) {

    protected abstract fun create(params: Params): Single<T>

    fun execute(params: Params): Single<T> = create(params).compose(transformer)
}