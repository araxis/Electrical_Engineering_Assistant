package com.vm.eea.di

import com.vm.eea.application.ITransactionProvider
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.application.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.application.project.IProjectRepository
import com.vm.eea.application.supplyPath.ISupplyPathService
import com.vm.eea.application.supplyPath.SupplyPathService
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.RoomTransactionProvider
import com.vm.eea.data.motor.MotorRepository
import com.vm.eea.data.panel.PanelRepository
import com.vm.eea.data.panelToMotorRelation.PanelToMotorRelationRepository
import com.vm.eea.data.panelToPanelRelation.PanelToPanelRelationRepository
import com.vm.eea.data.project.ProjectRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val dbModule=module{
    single{ AppDatabase.getInstance(androidApplication())}
    factory<ITransactionProvider> { RoomTransactionProvider(get()) }
    factory<IProjectRepository> { ProjectRepository(get()) }
    factory<ISupplyPathService> { SupplyPathService(get(),get()) }
    factory<IPanelRepository> { PanelRepository(get()) }
    factory<IMotorRepository> { MotorRepository(get()) }
    factory<IPanelToPanelRelationRepository> { PanelToPanelRelationRepository(get()) }
    factory<IPanelToMotorRelationRepository> { PanelToMotorRelationRepository(get()) }
}