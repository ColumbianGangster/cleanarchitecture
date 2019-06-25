package com.cleanarchitecture.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.cleanarchitecture.data.api.AlbumsApi
import com.cleanarchitecture.data.datastore.AlbumRemoteDataStore
import com.cleanarchitecture.data.repository.AlbumsRepositoryImpl
import com.cleanarchitecture.domain.albums.AlbumsRepository
import com.cleanarchitecture.domain.albums.GetAlbumsUseCase
import com.cleanarchitecture.presentation.common.AsyncSingleTransformer
import com.cleanarchitecture.presentation.common.ErrorUiMapper
import com.cleanarchitecture.presentation.mappers.AlbumUiMapper
import com.cleanarchitecture.presentation.navigation.AppNavigator
import com.cleanarchitecture.presentation.albums.AlbumsViewModel
import com.cleanarchitecture.presentation.common.FragmentsTransactionsManager
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModules = module {
    single(name = "remote") { AlbumRemoteDataStore(api = get(API)) }

    single { AlbumsRepositoryImpl(remote = get("remote")) as AlbumsRepository }
}

val useCaseModules = module {
    factory(name = "getNewsUseCase") { GetAlbumsUseCase(transformer = AsyncSingleTransformer(), repositories = get()) }
}

val networkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(AlbumsApi::class.java) }
}

val viewModels = module {
    viewModel {
        AlbumsViewModel(getAlbumsUseCase = get(GET_NEWS_USECASE), mapper = AlbumUiMapper(), uiErrorMapper = ErrorUiMapper())
    }
}

val navigator = module {
    factory { (activity: AppCompatActivity) -> AppNavigator(activity) }
}

val fragments = module {
    factory { (activity: AppCompatActivity) -> FragmentsTransactionsManager(activity.supportFragmentManager) }
}

private const val BASE_URL = "https://recs.richrelevance.com/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"