package com.jeremyg.difftest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DiffApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@DiffApplication)
            modules(myModule)
        }
        Timber.plant(Timber.DebugTree())
    }
}