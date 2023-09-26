package mx.mariovaldez.yapecodechallenge.splash.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.mariovaldez.yapecodechallenge.R
import mx.mariovaldez.yapecodechallenge.home.presentation.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        lifecycleScope.launch {
            delay(LAUNCH_DELAY)
            launchSearch()
        }
    }

    private fun launchSearch() {
        HomeActivity.launch(this)
        finish()
    }

    companion object {

        private const val LAUNCH_DELAY: Long = 1700
    }
}
