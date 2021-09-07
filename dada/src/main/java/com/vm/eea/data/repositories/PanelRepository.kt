package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.PanelEntity
import com.vm.eea.domain.SupplyPath
import com.vm.eea.domain.panel.IPanelRepository
import com.vm.eea.domain.panel.Panel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PanelRepository(private val db: AppDatabase): IPanelRepository {
    override fun getPanels(projectId: Long): Flow<List<Panel>> {
        return db.panelDao().all(projectId).map { it.map { o->o.toDomain() } }
    }


    override suspend fun updateSupplyPaths(projectId:Long, oldStartPath: SupplyPath, newStartPath: SupplyPath){
        db.panelDao().updateSupplyPaths(projectId,oldStartPath.path,newStartPath.path)
    }

    override fun getPanelsNotSupplyWith(projectId: Long, startPath: SupplyPath,): Flow<List<Panel>> {
        return db.panelDao().getPanelsNotSupplyWith(projectId,startPath.path)
            .map {list->list.map { it.toDomain() } }
    }

    override suspend fun getMdp(projectId: Long): Panel {
        return db.panelDao().getMdp(projectId).toDomain()
    }

    override suspend fun updateCode(panelId: Long,code:String,description:String){
        db.panelDao().updateCode(panelId,code,description)
    }

//    override suspend fun getLastPanel(projectId: Long): Panel {
//        return db.panelDao().getLastPanel(projectId).toMotorDomain()
//    }

    override suspend fun getPanel(panelId: Long): Panel {
        return db.panelDao().getPanel(panelId).toDomain()
    }

    override fun getPanel(projectId: Long, supplyPath: SupplyPath): Flow<Panel> {
        return db.panelDao().getPanelFlow(projectId,supplyPath.path).map { it.toDomain() }
    }

    override  fun getPanelFlow(panelId: Long): Flow<Panel> {
        return db.panelDao().getPanelFlow(panelId).map {it.toDomain() }
    }

    override suspend fun addPanel(panel: Panel):Long {
        val entity= PanelEntity(panel.projectId,panel.code,panel.description,panel.isMdp,panel.powerSupplyPath.path,panel.id)
       return  db.panelDao().insert(entity)
    }

    override suspend fun removePanel(panel: Panel) {
        db.panelDao().deleteById(panel.id)
    }
}