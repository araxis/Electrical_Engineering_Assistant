package com.vm.eea.di

import com.vm.eea.domain.ITransactionProvider
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.RoomTransactionProvider
import com.vm.eea.data.SupplyPathService
import com.vm.eea.data.repositories.*
import com.vm.eea.domain.ISupplyPathService
import com.vm.eea.domain.appDefaults.IAppDefaultsRepository
import com.vm.eea.domain.defaultAltitude.IDefaultAltitudeRepository

import com.vm.eea.domain.defaultGroundTemperature.IDefaultTemperatureRepository
import com.vm.eea.domain.defaultPowerfactor.IDefaultPowerfactorRepository
import com.vm.eea.domain.defaultSiolResistivity.IDefaultSoilResistivityRepository
import com.vm.eea.domain.defaultVoltage.IDefaultVoltageRepository
import com.vm.eea.domain.defaultWireSize.IDefaultWireSizeRepository
import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.domain.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.domain.project.IProjectRepository
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@FlowPreview
val dbModule=module{
    single { AppDatabase.getInstance(androidApplication()) }
    factory<ITransactionProvider> { RoomTransactionProvider(get()) }
    factory<IAppDefaultsRepository> { AppDefaultsRepository(get()) }
    factory<IProjectRepository> { ProjectRepository(get()) }
    factory<IDefaultVoltageRepository> { DefaultVoltageRepository(get()) }
    factory<IDefaultAltitudeRepository> { DefaultAltitudeRepository(get()) }
    factory<IDefaultTemperatureRepository> { DefaultTemperatureRepository(get()) }
    factory<IDefaultSoilResistivityRepository> { DefaultSoilResistivityRepository(get()) }
    factory<IDefaultPowerfactorRepository> { DefaultPowerfactorRepository(get()) }
    factory<IDefaultWireSizeRepository> { DefaultWireSizeRepository(get()) }
    factory<ISupplyPathService> { SupplyPathService(get()) }
    factory<IPanelRepository> { PanelRepository(get()) }
    factory<IMotorRepository> { MotorRepository(get()) }
    factory<IPanelToPanelRelationRepository> { PanelToPanelRelationRepository(get()) }
    factory<IPanelToMotorRelationRepository> { PanelToMotorRelationRepository(get()) }
}