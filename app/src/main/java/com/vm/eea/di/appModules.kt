package com.vm.eea.di

import com.vm.eea.application.supplyPath.IGetPanelChildPaths
import com.vm.eea.application.supplyPath.IGetPanelSupplyPath
import com.vm.eea.application.supplyPath.ISupplyPathService
import com.vm.eea.application.supplyPath.SupplyPathService
import com.vm.eea.data.supplyPath.GetPanelChildPaths
import com.vm.eea.data.supplyPath.GetPanelSupplyPath
import com.vm.eea.ui.NavigationManager
import org.koin.dsl.module

val appModules= module {
    single { NavigationManager() }
    factory<IGetPanelSupplyPath> { GetPanelSupplyPath(get()) }
    factory<IGetPanelChildPaths> { GetPanelChildPaths(get()) }
    factory<ISupplyPathService> { SupplyPathService(get(),get()) }

}