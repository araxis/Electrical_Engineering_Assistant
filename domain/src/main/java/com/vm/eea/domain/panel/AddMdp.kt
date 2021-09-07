package com.vm.eea.domain.panel

import com.vm.eea.domain.SupplyPath

class AddMdp(private val panelRepository: IPanelRepository) {
    suspend operator fun invoke(projectId:Long,code:String,description:String){

        val panel= Panel(projectId,code,description,true, SupplyPath("/1"),0)
        panelRepository.addPanel(panel)
    }
}