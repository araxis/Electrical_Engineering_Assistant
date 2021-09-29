package com.vm.eea.application.supplyPath

import com.vm.eea.application.PanelId


interface ISupplyPathService {


    suspend fun getNextSupplyPath(panelId: PanelId): ProjectSupplyPath


}

