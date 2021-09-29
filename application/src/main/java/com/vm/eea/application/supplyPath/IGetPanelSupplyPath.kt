package com.vm.eea.application.supplyPath

import com.vm.eea.application.PanelId

interface IGetPanelSupplyPath {

    suspend operator fun invoke(panelId: PanelId): ProjectSupplyPath
}