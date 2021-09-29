package com.vm.eea.data.supplyPath

import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.project.ProjectId
import com.vm.eea.application.supplyPath.IGetPanelSupplyPath
import com.vm.eea.application.supplyPath.ProjectSupplyPath
import com.vm.eea.data.AppDatabase

class GetPanelSupplyPath(private val db:AppDatabase): IGetPanelSupplyPath {
    override suspend fun invoke(panelId: PanelId): ProjectSupplyPath {

        return db.panelRadDao().getPanelInfo(panelId.id).let {
            ProjectSupplyPath(
                ProjectId(it.projectId),
                SupplyPath(it.supplyPath)
        ) }
    }
}