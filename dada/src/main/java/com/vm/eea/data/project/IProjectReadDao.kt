package com.vm.eea.data.project

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.application.*
import com.vm.eea.data.model.FullMotorRelationView
import com.vm.eea.data.model.FullProjectView
import kotlinx.coroutines.flow.Flow

@Dao
interface IProjectReadDao {

    @Query("select id,code,description,totalCurrent from full_project_view")
    fun getSimpleProjects():Flow<List<SimpleProjectDto>>

    @Query("select full_motor_view.powerSupplyPath from full_motor_view where full_motor_view.feeder_id=:panelId\n" +
            "union\n" +
            "select full_panel_view.powerSupplyPath from full_panel_view where full_panel_view.feederId=:panelId")
    suspend fun getPanelChildPaths(panelId:Long):List<String>

    @Query("select id,code,description from full_project_view where id= :projectId")
    fun getProjectCode(projectId: Long):Flow<ProjectCodeInfo>

    @Query("select id,altitude_value as value,altitude_unit as unit from projects where id= :projectId")
    suspend fun getProjectAltitude(projectId: Long): ProjectAltitude

    @Query("select circuit_count_value from projects where id= :projectId")
    suspend fun getProjectCircuitCount(projectId: Long):Int

    @Query("select id,conductor as value from projects where id= :projectId")
    suspend fun getProjectConductor(projectId: Long): ProjectConductor

    @Query("select insulation from projects where id= :projectId")
    suspend fun getProjectInsulation(projectId: Long): Insulation

    @Query("select methodOfInstallation from projects where id= :projectId")
    suspend fun getProjectMethodOfInstallation(projectId: Long): MethodOfInstallation

    @Query("select single_phase_powerfactor_value from projects where id= :projectId")
    suspend fun getProjectSinglePhaseCosPhi(projectId: Long):Double

    @Query("select two_phase_powerfactor_value from projects where id= :projectId")
    suspend fun getProjectTwoPhaseCosPhi(projectId: Long):Double

    @Query("select three_phase_powerfactor_value from projects where id= :projectId")
    suspend fun getProjectThreePhaseCosPhi(projectId: Long):Double

    @Query("select max_wire_size_value  from projects where id= :projectId")
    suspend fun getMaxWireSize(projectId: Long):Double

    @Query("select max_wire_size_value from projects where id= :projectId")
    suspend fun getMinWireSize(projectId: Long):Double


    @Query("select soil_thermal_resist_value as value, soil_thermal_resist_unit as unit from projects where id= :projectId")
    suspend fun getProjectSoilThermalResistivity(projectId: Long): ThermalResistivityDto

    @Query("select ambient_temp_value as value, ambient_temp_value as unit from projects where id= :projectId")
    suspend fun getProjectAmbientTemperature(projectId: Long): TemperatureDto

    @Query("select soil_temp_value as value, soil_temp_unit as unit from projects where id= :projectId")
    suspend fun getProjectSoilTemperature(projectId: Long): TemperatureDto

    @Query("select panel_motor_volt_drop_value from projects where id= :projectId")
    suspend fun getProjectPanelToMotorMaxVoltDrop(projectId: Long):Double

    @Query("select panel_panel_volt_drop_value from projects where id= :projectId")
    suspend fun getProjectPanelToPanelMaxVoltDrop(projectId: Long):Double

    @Query("select id,code,description,current from full_motor_view where projectId=:projectId")
    fun getSimpleProjectMotors(projectId:Long):Flow<List<SimpleProjectMotorDto>>

    @Query("select id,code,description,totalCurrent from full_panel_view where projectId=:projectId")
    fun getSimpleProjectPanels(projectId:Long):Flow<List<SimpleProjectPanelDto>>

    @Query("select * from full_project_view where id=:projectId")
    fun getFullProject(projectId: Long):Flow<FullProjectView>

    @Query("select * from full_motor_relation_view where id=:loadId")
    fun getFullMotorRelation(loadId: Long):Flow<FullMotorRelationView>

    @Query("select singlePhaseVoltageInVolt from projects where id=:projectId")
    suspend fun getSinglePhaseVoltage(projectId: Long): Double

    @Query("select twoPhaseVoltageInVolt from projects where id=:projectId")
    suspend fun getTwoPhaseVoltage(projectId: Long): Double

    @Query("select threePhaseVoltageInVolt from projects where id=:projectId")
    suspend fun getThreePhaseVoltage(projectId: Long): Double

    data class SimpleProjectDto(val id: Long, val code:String, val description:String,val totalCurrent:Double)
    data class SimpleProjectMotorDto(val id:Long,val code:String,val description:String,val current:Double)
    data class SimpleProjectPanelDto(val id:Long,val code:String,val description:String,val totalCurrent:Double)
    data class ProjectCodeInfo(val id: Long, val code:String, val description:String)
    data class ProjectAltitude(val id:Long,val value:Double,val unit: Length.Unit)
    data class ProjectConductor(val id:Long,val value: Conductor)
    data class ThermalResistivityDto(val value:Double,val unit: UnitOfThermalResistivity)
    data class TemperatureDto(val value:Double,val unit:UnitOfTemperature)
}