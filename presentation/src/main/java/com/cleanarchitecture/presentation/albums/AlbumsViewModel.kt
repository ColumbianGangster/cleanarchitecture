package com.cleanarchitecture.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cleanarchitecture.domain.albums.DomainAlbum
import com.cleanarchitecture.domain.albums.GetAlbumsUseCase
import com.cleanarchitecture.domain.common.Mapper
import com.cleanarchitecture.presentation.common.BaseViewModel
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.extensions.addTo

class AlbumsViewModel(private val getAlbumsUseCase: GetAlbumsUseCase,
                      private val mapper: Mapper<DomainAlbum, UiAlbum>,
                      private val uiErrorMapper: Mapper<Throwable, UiError>) : BaseViewModel() {

    companion object {
        const val TAG = "AlbumsViewModel"
    }

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val contentLiveData = MutableLiveData<List<UiAlbum>>()
    private val errorLiveData = MutableLiveData<UiError>()

    fun getAlbums() {
        getAlbumsUseCase.execute()
                .map { mapper.mapList(it) }
                .doOnSubscribe { loadingLiveData.value = true }
                .doOnEvent { _, _ -> loadingLiveData.value = false }
                .subscribe({ response: List<UiAlbum> -> contentLiveData.value = response },
                        { error: Throwable -> errorLiveData.value = uiErrorMapper.map(error) })
                .addTo(compositeDisposable)

    }

    fun getLoadingObservable(): LiveData<Boolean> = loadingLiveData
    fun getContentObservable(): LiveData<List<UiAlbum>> = contentLiveData
    fun getErrorObservable(): LiveData<UiError> = errorLiveData

}