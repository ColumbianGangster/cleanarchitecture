package com.cleanarchitecture.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.presentation.navigation.AppNavigator
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit
import com.richrelevance.recommendations.RecommendedProduct
import com.richrelevance.recommendations.PlacementResponse
import com.richrelevance.recommendations.PlacementResponseInfo
import com.richrelevance.RichRelevance
import com.richrelevance.recommendations.Placement
import android.util.Log
import com.richrelevance.Callback


class SplashActivity : AppCompatActivity() {

    private val navigator: AppNavigator by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Completable.complete().delay(3000, TimeUnit.MILLISECONDS)
                .doOnComplete {
                    navigator.toHome()
                    finish()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
