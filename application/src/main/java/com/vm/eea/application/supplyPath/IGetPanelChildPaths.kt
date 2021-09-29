package com.vm.eea.application.supplyPath

import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath


interface IGetPanelChildPaths {

    suspend operator fun invoke(panelId: PanelId):List<SupplyPath>
}