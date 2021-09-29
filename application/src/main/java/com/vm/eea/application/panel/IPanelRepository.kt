package com.vm.eea.application.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.project.ProjectId

interface IPanelRepository {

    suspend fun add(panel: Panel): PanelId
    suspend fun updateCode(panelId: PanelId, code: PanelCode, description: String)
    suspend fun updateDemandFactor(panelId: PanelId,value:CosPhi)
    suspend fun replaceSupplyPaths(projectId: ProjectId, oldStartPath: SupplyPath, newStartPath: SupplyPath)
}