package com.vm.eea.data.panel

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.application.SupplyPath
import com.vm.eea.application.project.ProjectId
import com.vm.eea.data.model.FullPanelRelationView
import kotlinx.coroutines.flow.Flow

@Dao
interface IPanelReadDao {

    @Query("select id,code,description from panels where projectId=:projectId")
    suspend fun getSimplePanels(projectId:Long):List<SimplePanelDto>

    @Query("select * from full_panel_relation_view where id=:panelId")
    fun getFullPanelRelationViewFlow(panelId: Long): Flow<FullPanelRelationView>

    @Query("select projectId,powerSupplyPath as supplyPath from full_panel_relation_view where id=:panelId")
    fun getPanelInfo(panelId: Long): PanelInfo

    @Query("select id,code,description from panels where id=:panelId")
    fun getSimplePanel(panelId: Long): SimplePanelDto



    @Query("Select id,code,description from panels where(projectId=:projectId and powerSupplyPath not LIKE :startPath || '%')")
    fun getPanelsNotSupplyWith(projectId: Long, startPath: String):List<SimplePanelDto>

    data class SimplePanelDto(val id:Long,val code:String,val description:String)
    data class PanelInfo(val projectId: Long,val supplyPath:String)
}