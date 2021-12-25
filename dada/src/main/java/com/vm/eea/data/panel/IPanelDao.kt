package com.vm.eea.data.panel

import android.database.sqlite.SQLiteException
import androidx.room.*
import com.vm.eea.application.Power
import com.vm.eea.data.model.FullPanelView
import kotlinx.coroutines.flow.Flow

@Dao
interface IPanelDao {


    @Query("select exists( select 1 from panels where (projectId = :projectId and code=:code))")
    fun isExist(projectId: Long, code: String): Boolean

      @Query("select projectId,powerSupplyPath as supplyPath from panels where id=:panelId")
      fun getPanelPathInfo(panelId: Long): PanelPathInfo

    @Query("select projectId,demand_factor_value as demandFactor,id as panelId,projectLineToLineVoltageInVolt as lineLineVoltage,projectLineToNullVoltageInVolt as lineNullVoltage ,coincidenceFactor   from full_panel_view where projectCode=:projectCode")
    fun getApplicationPanelFlow(projectCode:String): Flow<ApplicationPanelDto>

    @Query("SELECT * FROM panels where projectId = :projectId")
    fun all(projectId:Long): Flow<List<PanelEntity>>

    @Query("select * from full_panel_view where projectId= :projectId")
    fun getProjectPanels(projectId:Long):Flow<List<FullPanelView>>

    @Query("SELECT * FROM panels where id = :panelId")
    suspend fun getPanel(panelId: Long): PanelEntity

    @Query("SELECT * FROM panels where id = :panelId")
    fun getPanelFlow(panelId: Long): Flow<PanelEntity>

    @Query("SELECT powerSupplyPath FROM panels where id = :panelId")
    fun getPanelSupplyPath(panelId: Long):String

    @Query("SELECT * FROM panels where (powerSupplyPath = :supplyPath and projectId= :projectId)")
    fun getPanelFlow(projectId: Long,supplyPath: String): Flow<PanelEntity>



    @Query("UPDATE panels SET code = :code,description=:description WHERE id = :panelId")
    suspend fun updateCode(panelId: Long,code:String,description:String)


//    @Query("SELECT MAX(id) as id,code,description,isMdp,powerSupplyPath,projectId FROM panels where projectId= :projectId")
//    suspend fun getLastPanel(projectId: Long): PanelEntity

    @Query("SELECT * FROM panels where ( projectId = :projectId and isMdp =1)")
    suspend fun getMdp(projectId: Long): PanelEntity

//    @Query("Select * from panels where(projectId=:projectId and powerSupplyPath not LIKE :startPath || '%')")
//    fun getPanelsNotSupplyWith(projectId:Long,startPath:String):Flow<List<PanelEntity>>

       @Query("Select id,code,description from panels where(projectId=:projectId and powerSupplyPath not LIKE :startPath || '%')")
       fun getPanelsNotSupplyWith(projectId: Long, startPath: String):List<SimplePanelDto>

     @Query("select loadId as motorId,power_value as power,power_unit as powerUnit,current from full_motor_view where feeder_id=:panelId")
     fun getPanelMotors(panelId: Long): Flow<List<PanelMotorDto>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: PanelEntity):Long

    @Query("DELETE FROM panels WHERE id = :panelId")
    suspend fun deleteById(panelId: Long)

    @Query("UPDATE panels SET powerSupplyPath = :path WHERE id = :panelId")
    fun updatePath(panelId: Long,path:String): Int

    @Query("UPDATE panels SET powerSupplyPath = `replace`(powerSupplyPath, :oldStartPath, :newStartPath ) WHERE (projectId = :projectId and powerSupplyPath LIKE :oldStartPath || '%')")
    suspend fun updateSupplyPaths(projectId:Long,oldStartPath: String,newStartPath:String)

    @Query("UPDATE panels SET demand_factor_value = :value WHERE id = :panelId")
    fun updateDemandFactor(panelId: Long, value: Double)

    @Query("select projectLineToNullVoltageInVolt as lineNullVoltage ,projectLineToLineVoltageInVolt as lineLineVoltage,demand_factor_value as demandFactor,totalCurrent,totalActivePower,totalApparentPower,totalReactivePower,coincidenceFactor from full_panel_view where id=:panelId")
    fun getPanelInfo(panelId: Long): PanelInfoDto

    @Query("UPDATE panels SET coincidenceFactor = :value WHERE id = :panelId")
    fun updateCoincidenceFactor(panelId: Long, value: Double)



    @Update
    suspend fun update(panelEntity: PanelEntity)

    data class PanelInfoDto( val totalCurrent:Double,
                             val demandFactor: Double,
                             val coincidenceFactor:Double,
                             val totalApparentPower:Double,
                             val totalReactivePower: Double,
                             val totalActivePower:Double,
                             val lineLineVoltage: Double,
                             val lineNullVoltage: Double)

        data class ApplicationPanelDto(val panelId:Long,
                                   val projectId: Long,
                                   val lineNullVoltage:Double,
                                   val lineLineVoltage:Double,
                                   val coincidenceFactor:Double,
                                   val demandFactor:Double )

    data class PanelPathInfo(val projectId: Long, val supplyPath:String)
    data class SimplePanelDto(val id:Long,val code:String,val description:String)
    data class PanelMotorDto(val motorId:Long, val power:Double, val powerUnit: Power.Unit, val current: Double)


}