package com.vm.eea

import android.app.Application
import com.vm.eea.di.*
import com.vm.eea.di.motorModule
import com.vm.eea.di.projectModule
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
            modules(dbModule, motorModule, panelModule, projectModule,
                calculatorsModule , appModules)
        }
    }
}