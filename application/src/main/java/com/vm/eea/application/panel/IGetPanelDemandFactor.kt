package com.vm.eea.application.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId

interface IGetPanelDemandFactor {

    suspend operator fun invoke(panelId: PanelId):CosPhi
}