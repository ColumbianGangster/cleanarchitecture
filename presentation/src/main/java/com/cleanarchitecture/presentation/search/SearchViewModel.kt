package com.cleanarchitecture.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.domain.searchnavigation.DomainSearchNavigation
import com.cleanarchitecture.domain.searchnavigation.GetProductsBySearchNavigationUseCase
import com.cleanarchitecture.presentation.common.BaseViewModel
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.extensions.addTo

class SearchViewModel(private val getProductsBySearchNavigationUseCase: GetProductsBySearchNavigationUseCase,
                      private val mapper: Mapper<DomainSearchNavigation, UiSearchNavigation>,
                      private val uiErrorMapper: Mapper<Throwable, UiError>) : BaseViewModel() {

    companion object {
        private val TAG = "SearchViewModel"
    }

    var loadingLiveData = MutableLiveData<Boolean>()
    var contentLiveData = MutableLiveData<UiSearchNavigation>()
    var errorLiveData = MutableLiveData<UiError>()

    fun getProducts() {
        getProductsBySearchNavigationUseCase.execute()
                .map { mapper.map(it) }
                .subscribe({ response: UiSearchNavigation ->
                    contentLiveData.value = response
                }, { error: Throwable ->
                    Log.d(TAG, error.message)
                    errorLiveData.value = uiErrorMapper.map(error)
                }).addTo(compositeDisposable)
    }
}