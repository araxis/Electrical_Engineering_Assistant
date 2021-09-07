package com.vm.eea.domain.panel

class GetPanels(private val panelRepository: IPanelRepository) {

    operator fun invoke(projectId:Long)=panelRepository.getPanels(projectId )
}