package com.vm.eea.application.panel.update

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.panel.IPanelRepository

class UpdatePanelDemandFactor(private val repository: IPanelRepository) {

    suspend operator fun invoke(panelId:PanelId,value:CosPhi){
        repository.updateDemandFactor(panelId,value)
    }
}