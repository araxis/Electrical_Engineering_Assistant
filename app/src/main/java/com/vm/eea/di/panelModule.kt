package com.vm.eea.di

import com.vm.eea.application.panel.addPanel.AddPanel
import com.vm.eea.application.panel.addPanel.IGetDefaultPanelToPanelRelationConfigs
import com.vm.eea.data.panelToPanelRelation.GetPanelToPanelDefaultRelationConfig
import org.koin.dsl.module

val panelModule= module {
    factory<IGetDefaultPanelToPanelRelationConfigs> { GetPanelToPanelDefaultRelationConfig(get()) }
    factory { AddPanel(get(),get(),get(),get(),get()) }








}