package com.vm.eea.application.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.project.ProjectId

interface IPanelRepository {

    suspend fun add(panel: Panel): PanelId
    suspend fun getInfo(panelId: PanelId):PanelInfo
    suspend fun get(panelId: PanelId):Panel
    suspend fun update(panel: Panel)
    suspend fun replaceSupplyPaths(projectId: ProjectId, oldStartPath: SupplyPath, newStartPath: SupplyPath)
    suspend fun isDuplicated(projectId: ProjectId, code: PanelCode): Boolean
    suspend fun updateDemandFactor(panelId: PanelId, value: CosPhi)
    suspend fun updateCoincidenceFactor(panelId: PanelId, value: CoincidenceFactor)
}