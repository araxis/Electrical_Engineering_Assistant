package com.vm.eea.data.panel

import com.vm.eea.application.*
import com.vm.eea.application.panel.*
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.AppDatabase

class PanelRepository(private val db: AppDatabase): IPanelRepository {





    override suspend fun isDuplicated(projectId: ProjectId, code: PanelCode): Boolean {
        return db.panelDao().isExist(projectId.id,code.value)
    }

    override suspend fun replaceSupplyPaths(projectId:ProjectId, oldStartPath: SupplyPath, newStartPath: SupplyPath){
        db.panelDao().updateSupplyPaths(projectId(),oldStartPath.path,newStartPath.path)
    }



    override suspend fun updateDemandFactor(panelId: PanelId, value: CosPhi) {
        db.panelDao().updateDemandFactor(panelId.id,value.value)
    }

    override suspend fun updateCoincidenceFactor(panelId: PanelId, value: CoincidenceFactor) {
        db.panelDao().updateCoincidenceFactor(panelId.id,value.value)
    }


    override suspend fun add(panel: Panel):PanelId {

            val entity= PanelEntity(panel.projectId.id,panel.code.value,panel.description,
                panel.isMdp,panel.demandFactor,panel.coincidenceFactor.value,panel.powerSupplyPath.path,0)
            val id=  db.panelDao().insert(entity)
        return PanelId(id)


    }

    override suspend fun getInfo(panelId: PanelId): PanelInfo {
        val entity=db.panelDao().getPanelInfo(panelId.id)
        return with(entity){
            PanelInfo(
                current = totalCurrent.A,
                coincidenceFactor = CoincidenceFactor(coincidenceFactor),
                apparentPower = totalApparentPower.VA,
                reactivePower = totalReactivePower.VAr,
                demandFactor = CosPhi(demandFactor),
                activePower = totalActivePower.W,
                lineNullVoltage = lineNullVoltage.v,
                lineLineVoltage = lineLineVoltage.v
            )
        }
    }

    override suspend fun get(panelId: PanelId): Panel {
        val entity=db.panelDao().getPanel(panelId.id)
        return with(entity){
            Panel(
                projectId=ProjectId(projectId),
                code= PanelCode(code),
                description=description,
                isMdp=isMdp,
                powerSupplyPath=SupplyPath(powerSupplyPath),
                coincidenceFactor = CoincidenceFactor(coincidenceFactor),
                demandFactor = demandFactor,
            )
        }
    }

    override suspend fun update(panel: Panel) {
        val entity= with(panel){
            PanelEntity(
                projectId=projectId.id,
                code= code.value,
                description=description,
                isMdp=isMdp,
                powerSupplyPath=powerSupplyPath.path,
                coincidenceFactor = coincidenceFactor.value,
                demandFactor = demandFactor,
            )
        }
        db.panelDao().update(entity)
    }


}