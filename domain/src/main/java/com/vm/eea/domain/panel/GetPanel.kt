package com.vm.eea.domain.panel

import kotlinx.coroutines.flow.Flow

class GetPanel(private val panelRepository:IPanelRepository) {
    operator fun invoke(projectId:Long): Flow<Panel> {
        return panelRepository.getPanelFlow(projectId)
    }
}