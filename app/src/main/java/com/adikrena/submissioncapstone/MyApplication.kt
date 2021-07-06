package com.adikrena.submissioncapstone

import android.app.Application
import com.adikrena.submissioncapstone.core.di.databaseModule
import com.adikrena.submissioncapstone.core.di.networkModule
import com.adikrena.submissioncapstone.core.di.repositoryModule
import com.adikrena.submissioncapstone.di.useCaseModule
import com.adikrena.submissioncapstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    databaseModule,
                    useCaseModule
                )
            )
        }
    }
}