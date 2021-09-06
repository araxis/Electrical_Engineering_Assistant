package com.vm.eea

import android.app.Application
import com.vm.eea.di.appModules
import com.vm.eea.di.dbModule
import com.vm.eea.di.viewModels
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class App : Application() {



    @FlowPreview
    override fun onCreate() {
        super.onCreate()
        //App.database = AppDatabase.getInstance(this)
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(dbModule, viewModels, appModules)
        }
    }
}