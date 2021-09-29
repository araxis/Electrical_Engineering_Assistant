package com.vm.eea.application.panel

import com.vm.eea.application.PanelId

interface IGetPanelCode {
    suspend operator fun invoke(panelId: PanelId):SimplePanel
}