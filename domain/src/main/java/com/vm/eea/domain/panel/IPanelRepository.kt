package com.vm.eea.domain.panel

import com.vm.eea.domain.SupplyPath
import kotlinx.coroutines.flow.Flow


interface IPanelRepository {

    fun getPanels(projectId:Long): Flow<List<Panel>>
    fun getPanelsNotSupplyWith(projectId:Long,startPath: SupplyPath):Flow<List<Panel>>
    suspend fun getMdp(projectId: Long):Panel
//    suspend fun getLastPanel(projectId:Long):Panel
    suspend fun getPanel(panelId:Long): Panel



    fun getPanel(projectId: Long,supplyPath: SupplyPath):Flow<Panel>

    fun getPanelFlow(panelId:Long):Flow<Panel>
    suspend fun addPanel(panel: Panel):Long
    suspend fun removePanel(panel: Panel)
    suspend fun updateCode(panelId: Long, code: String, description: String)
    suspend fun updateSupplyPaths(
        projectId: Long,
        oldStartPath: SupplyPath,
        newStartPath: SupplyPath
    )
}