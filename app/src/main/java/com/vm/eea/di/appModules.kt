package com.vm.eea.di

import com.vm.eea.ui.NavigationManager
import org.koin.dsl.module

val appModules= module {
    single { NavigationManager() }

}