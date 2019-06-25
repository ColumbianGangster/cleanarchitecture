package com.cleanarchitecture.presentation.common

import com.cleanarchitecture.domain.common.Mapper


class ErrorUiMapper : Mapper<Throwable, UiError> {

    override fun map(from: Throwable) = UiError(
            title = "Error",
            message = from.message,
            errorViewType = ErrorViewType.DIALOG,
            cancelable = true,
            throwable = from
    )
}
