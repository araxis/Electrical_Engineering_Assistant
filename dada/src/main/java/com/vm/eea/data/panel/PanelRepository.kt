package com.vm.eea.data.panel

import com.vm.eea.application.CosPhi
import com.vm.eea.application.PanelId
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.panel.IPanelRepository
import com.vm.eea.application.panel.Panel
import com.vm.eea.application.panel.PanelCode
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.PanelEntity

class PanelRepository(private val db: AppDatabase): IPanelRepository {

    override suspend fun replaceSupplyPaths(projectId:ProjectId, oldStartPath: SupplyPath, newStartPath: SupplyPath){
        db.panelDao().updateSupplyPaths(projectId(),oldStartPath.path,newStartPath.path)
    }

    override suspend fun updateCode(panelId: PanelId, code: PanelCode, description:String){
        db.panelDao().updateCode(panelId.id,code.value,description)
    }

    override suspend fun updateDemandFactor(panelId: PanelId, value: CosPhi) {
        db.panelDao().updateDemandFactor(panelId.id,value.value)
    }


    override suspend fun add(panel: Panel):PanelId {

            val entity= PanelEntity(panel.projectId.id,panel.code.value,panel.description,
                panel.isMdp,panel.demandFactor,panel.powerSupplyPath.path,0)
            val id=  db.panelDao().insert(entity)
        return PanelId(id)


    }


}