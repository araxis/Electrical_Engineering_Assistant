package com.vm.eea.domain.panel

class GetMdp(private val panelRepository: IPanelRepository) {

    suspend operator fun invoke(projectId:Long):Panel{
        return panelRepository.getMdp(projectId)
    }
}