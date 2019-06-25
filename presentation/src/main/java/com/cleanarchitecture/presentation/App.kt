package com.cleanarchitecture.presentation

import android.app.Application
import com.cleanarchitecture.presentation.di.*
import com.richrelevance.ClientConfiguration
import com.richrelevance.RRLog
import com.richrelevance.RichRelevance
import org.koin.android.ext.android.startKoin
import java.util.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()

        // First create a configuration and use it to configure the default clientl.
        RichRelevance.init(
                this,
                ClientConfiguration("showcaseparent", "bccfa17d092268c0").apply {
                    apiClientSecret = "r5j50mlag06593401nd4kt734i"
                    userId = "RZTestUserTest"
                    sessionId = UUID.randomUUID().toString()
                }
        )

        // Enable all logging
        RichRelevance.setLoggingLevel(RRLog.VERBOSE)
    }

    private fun loadKoin() {
        startKoin(this,
                listOf(networkModules,
                        viewModels,
                        repositoryModules,
                        useCaseModules,
                        navigator,
                        fragments
                )

        )
    }
}

