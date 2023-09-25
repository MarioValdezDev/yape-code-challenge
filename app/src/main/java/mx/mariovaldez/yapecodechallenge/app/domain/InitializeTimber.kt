package mx.mariovaldez.yapecodechallenge.app.domain

import mx.mariovaldez.yapecodechallenge.BuildConfig
import timber.log.Timber

internal class InitializeTimber {

    operator fun invoke() {
        if (BuildConfig.DEBUG) {
            println("InitializeTimber")
            Timber.plant(Timber.DebugTree())
        }
    }
}
