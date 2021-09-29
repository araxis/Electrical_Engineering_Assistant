package com.vm.eea.data.supplyPath

import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.supplyPath.IGetPanelChildPaths
import com.vm.eea.data.AppDatabase

class GetPanelChildPaths(private val db: AppDatabase): IGetPanelChildPaths {
    override suspend fun invoke(panelId: PanelId): List<SupplyPath> {
       return db.projectReadDao().getPanelChildPaths(panelId.id)
           .map { SupplyPath(it) }
    }
}