package com.vm.eea.application.panel

import com.vm.eea.application.PanelId

interface IGetAvailablePanelFeeders {
    suspend operator fun invoke(panelId: PanelId): List<SimplePanel>
}