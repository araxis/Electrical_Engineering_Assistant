package com.vm.eea.application.panel.addPanel

import com.vm.eea.application.PanelId

interface IGetDefaultPanelToPanelRelationConfigs {

    suspend operator fun invoke(panelId: PanelId):PanelToPanelRelationConfig

}