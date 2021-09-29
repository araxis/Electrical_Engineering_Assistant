package com.vm.eea.application.supplyPath

import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath


class SupplyPathService(
    private val getPanelSupplyPath: IGetPanelSupplyPath,
    private val getPanelChildPaths: IGetPanelChildPaths): ISupplyPathService {


    override suspend fun getNextSupplyPath(panelId: PanelId): ProjectSupplyPath {
        val panelPath=getPanelSupplyPath(panelId)
        val levePaths=getPanelChildPaths(panelId)
        val id=if(levePaths.isEmpty()) 1 else levePaths.maxOf { it.idInLevel } +1
        val path= SupplyPath("${panelPath.supplyPath()}/$id")
        return ProjectSupplyPath(panelPath.projectId,path)
    }
}