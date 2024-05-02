package pl.wincenciuk.imagesearcher

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.wincenciuk.imagesearcher.di.mainModule

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(mainModule)
        }
    }
}