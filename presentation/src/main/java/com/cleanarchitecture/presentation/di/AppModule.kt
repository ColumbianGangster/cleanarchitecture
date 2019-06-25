package com.cleanarchitecture.presentation.di

import androidx.appcompat.app.AppCompatActivity
import com.cleanarchitecture.data.api.AlbumsApi
import com.cleanarchitecture.data.api.RichRelevanceApi
import com.cleanarchitecture.data.api.SearchApi
import com.cleanarchitecture.data.datastore.AlbumRemoteDataStore
import com.cleanarchitecture.data.datastore.RichRelevanceRemoteDataStore
import com.cleanarchitecture.data.datastore.SearchRemoteDataStore
import com.cleanarchitecture.data.repository.AlbumsRepositoryImpl
import com.cleanarchitecture.data.repository.RichRelevanceRepositoryImpl
import com.cleanarchitecture.data.repository.SearchRepositoryImpl
import com.cleanarchitecture.domain.albums.AlbumsRepository
import com.cleanarchitecture.domain.albums.GetAlbumsUseCase
import com.cleanarchitecture.domain.richrelevance.GetRecommendedProductsUseCase
import com.cleanarchitecture.domain.richrelevance.RichRelevanceRepository
import com.cleanarchitecture.domain.searchnavigation.GetProductsBySearchNavigationUseCase
import com.cleanarchitecture.domain.searchnavigation.SearchRepository
import com.cleanarchitecture.presentation.albums.AlbumsViewModel
import com.cleanarchitecture.presentation.common.AsyncSingleTransformer
import com.cleanarchitecture.presentation.common.ErrorUiMapper
import com.cleanarchitecture.presentation.common.FragmentsTransactionsManager
import com.cleanarchitecture.presentation.mappers.AlbumUiMapper
import com.cleanarchitecture.presentation.mappers.SearchNavigationUiMapper
import com.cleanarchitecture.presentation.navigation.AppNavigator
import com.cleanarchitecture.presentation.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModules = module {
    single(name = ALBUM_REMOTE_DATASTORE) { AlbumRemoteDataStore(api = get(ALBUMS_API)) }
    single(name = SEARCH_REMOTE_DATASTORE) { SearchRemoteDataStore(api = get(SEARCH_API)) }
    single(name = RICHRELEVANCE_REMOTE_DATASTORE) { RichRelevanceRemoteDataStore(api = get(RICHRELEVANCE_API)) }

    single { AlbumsRepositoryImpl(remote = get(ALBUM_REMOTE_DATASTORE)) as AlbumsRepository }
    single { SearchRepositoryImpl(remote = get(SEARCH_REMOTE_DATASTORE)) as SearchRepository }
    single { RichRelevanceRepositoryImpl(remote = get(RICHRELEVANCE_REMOTE_DATASTORE)) as RichRelevanceRepository }

}

val useCaseModules = module {
    factory(name = GET_NEWS_USECASE) { GetAlbumsUseCase(transformer = AsyncSingleTransformer(), repositories = get()) }
    factory(name = GET_RECOMMENDED_PRODUCTS_USECASE) { GetRecommendedProductsUseCase(transformer = AsyncSingleTransformer(), repository = get()) }
    factory(name = GET_PRODUCTS_BY_SEARCHNAVIGATION_USECASE) { GetProductsBySearchNavigationUseCase(transformer = AsyncSingleTransformer(), repositories = get()) }

}

val networkModules = module {
    single(name = RETROFIT_INSTANCE1) { createNetworkClient(BASE_URL1) }
    single(name = RETROFIT_INSTANCE2) { createNetworkClient(BASE_URL2) }
    single(name = RETROFIT_INSTANCE3) { createNetworkClient(BASE_URL3) }

    single(name = ALBUMS_API) { (get(RETROFIT_INSTANCE1) as Retrofit).create(AlbumsApi::class.java) }
    single(name = SEARCH_API) { (get(RETROFIT_INSTANCE2) as Retrofit).create(SearchApi::class.java) }
    single(name = RICHRELEVANCE_API) { (get(RETROFIT_INSTANCE3) as Retrofit).create(RichRelevanceApi::class.java) }
}

val viewModels = module {
    viewModel {
        AlbumsViewModel(getAlbumsUseCase = get(GET_NEWS_USECASE), mapper = AlbumUiMapper(), uiErrorMapper = ErrorUiMapper())
    }
    viewModel {
        SearchViewModel(getProductsBySearchNavigationUseCase = get(GET_PRODUCTS_BY_SEARCHNAVIGATION_USECASE), mapper = SearchNavigationUiMapper(), uiErrorMapper = ErrorUiMapper())
    }
}

val navigator = module {
    factory { (activity: AppCompatActivity) -> AppNavigator(activity) }
}

val fragments = module {
    factory { (activity: AppCompatActivity) -> FragmentsTransactionsManager(activity.supportFragmentManager) }
}

private const val BASE_URL1 = "https://jsonplaceholder.typicode.com/"
private const val BASE_URL2 = "https://api.dcg-search.com/currys/"
private const val BASE_URL3 = "https://recs.richrelevance.com/"

private const val RETROFIT_INSTANCE1 = "RETROFIT_INSTANCE1"
private const val RETROFIT_INSTANCE2 = "RETROFIT_INSTANCE2"
private const val RETROFIT_INSTANCE3 = "RETROFIT_INSTANCE3"

private const val ALBUMS_API = "AlbumsApi"
private const val SEARCH_API = "SearchApi"
private const val RICHRELEVANCE_API = "RichRelevanceApi"

private const val ALBUM_REMOTE_DATASTORE = "AlbumRemoteDataStore"
private const val SEARCH_REMOTE_DATASTORE = "SearchRemoteDataStore"
private const val RICHRELEVANCE_REMOTE_DATASTORE = "RICHRELEVANCE_REMOTE_DATASTORE"

private const val GET_PRODUCTS_BY_SEARCHNAVIGATION_USECASE = "getProductsBySearchNavigationUseCase"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val GET_RECOMMENDED_PRODUCTS_USECASE = "GET_RECOMMENDED_PRODUCTS_USECASE"
