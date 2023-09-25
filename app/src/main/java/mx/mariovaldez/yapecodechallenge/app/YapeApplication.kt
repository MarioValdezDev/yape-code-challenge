package mx.mariovaldez.yapecodechallenge.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mx.mariovaldez.yapecodechallenge.app.domain.InitializeTimber

@HiltAndroidApp
class YapeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        InitializeTimber().invoke()
    }
}
