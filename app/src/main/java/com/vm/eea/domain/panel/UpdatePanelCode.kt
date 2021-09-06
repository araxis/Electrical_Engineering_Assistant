package com.vm.eea.domain.panel

class UpdatePanelCode(private val panelRepository: IPanelRepository) {
    suspend operator fun invoke(panelId:Long, code:String, description:String){

        panelRepository.updateCode(panelId,code,description)
    }
}