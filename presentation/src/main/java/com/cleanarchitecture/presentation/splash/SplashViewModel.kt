package com.cleanarchitecture.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.presentation.common.BaseViewModel
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.extensions.addTo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(private val uiErrorMapper: Mapper<Throwable, UiError>) : BaseViewModel() {

    private val contentLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<UiError>()

    fun onLaunch() {
        //No need for subscribeOn as Timer uses computation scheduler by default
        //If check is used to prevent spawning of multiple threads
        if (contentLiveData.value == null) {
            Completable.timer(TIMEOUT, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ contentLiveData.value = true },
                            { errorLiveData.value = uiErrorMapper.map(it) })
                    .addTo(compositeDisposable)
        }
    }

    fun getContentObservable(): LiveData<Boolean> = contentLiveData
    fun getErrorObservable(): LiveData<UiError> = errorLiveData

    companion object {
        private const val TIMEOUT = 1000L
    }
}