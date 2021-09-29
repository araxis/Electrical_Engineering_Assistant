package com.vm.eea.data.project

import androidx.room.*
import com.vm.eea.application.*
import com.vm.eea.data.model.ProjectEntity

@Dao
interface IProjectDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProject(projectEntity: ProjectEntity):Long

    @Update
    suspend fun update(projectEntity: ProjectEntity)

    @Delete
    fun delete(model: ProjectEntity)

    @Query("DELETE FROM projects WHERE id = :projectId")
    suspend fun deleteById(projectId: Long)

    @Query("UPDATE projects SET isDeleted = 1 WHERE id = :projectId")
    suspend fun softDelete(projectId: Long)

    @Query("UPDATE projects SET code = :code,description=:description WHERE id = :projectId")
    suspend fun updateCode(projectId: Long,code:String,description:String)



    @Query("UPDATE projects SET singlePhaseVoltageInVolt = :value WHERE id = :projectId")
    suspend fun updateOnePhaseVoltage(projectId: Long, value: Double)

    @Query("UPDATE projects SET threePhaseVoltageInVolt = :value WHERE id = :projectId")
    suspend fun updateThreePhaseVoltage(projectId: Long, value: Double)



    @Query("UPDATE projects SET altitude_value = :value,altitude_unit  =:unit WHERE id = :projectId")
    suspend fun updateAltitude(projectId: Long, value:Double, unit: Length.Unit)

    @Query("UPDATE projects SET methodOfInstallation = :value WHERE id = :projectId")
    suspend fun updateMethodOfInstallation(projectId: Long,value: MethodOfInstallation)

    @Query("UPDATE projects SET ambient_temp_value = :value,ambient_temp_unit  =:unit WHERE id = :projectId")
    suspend fun updateAmbientTemperature(projectId: Long, value:Double, unit: UnitOfTemperature)

    @Query("UPDATE projects SET soil_temp_value = :value,soil_temp_unit  =:unit WHERE id = :projectId")
    suspend fun updateSoilTemperature(projectId: Long, value:Double, unit: UnitOfTemperature)

    @Query("UPDATE projects SET soil_thermal_resist_value = :value,soil_thermal_resist_unit  =:unit WHERE id = :projectId")
    suspend fun updateSoilResistivity(projectId: Long, value:Double, unit: UnitOfThermalResistivity)

    @Query("UPDATE projects SET conductor = :value WHERE id = :projectId")
    suspend fun updateConductor(projectId: Long,value: Conductor)

    @Query("UPDATE projects SET insulation = :value WHERE id = :projectId")
    suspend fun updateInsulation(projectId: Long,value: Insulation)

    @Query("UPDATE projects SET single_phase_powerfactor_value = :value WHERE id = :projectId")
    suspend fun updateSinglePhasePowerFactor(projectId: Long,value:Double)

    @Query("UPDATE projects SET three_phase_powerfactor_value = :value WHERE id = :projectId")
    suspend fun updateThreePhasePowerFactor(projectId: Long,value:Double)

    @Query("UPDATE projects SET panel_panel_volt_drop_value = :value WHERE id = :projectId")
    suspend fun updatePanelToPanelMaxVoltDrop(projectId: Long,value:Double)

    @Query("UPDATE projects SET panel_motor_volt_drop_value = :value WHERE id = :projectId")
    suspend fun updatePanelToMotorMaxVoltDrop(projectId: Long,value:Double)

    @Query("UPDATE projects SET circuit_count_value = :value WHERE id = :projectId")
    suspend fun updateCircuitInTheSameConduit(projectId: Long,value:Int)

    @Query("UPDATE projects SET max_wire_size_value = :value,max_wire_size_unit  =:unit WHERE id = :projectId")
    suspend fun updateMaxCableSize(projectId: Long,value:Double,unit: UnitOfWireSize)

    @Query("UPDATE projects SET min_wire_size_value = :value,min_wire_size_unit  =:unit WHERE id = :projectId")
    suspend fun updateMinCableSize(projectId: Long,value:Double,unit: UnitOfWireSize)

    @Query("UPDATE projects SET standard = :value WHERE id = :projectId")
    suspend fun updateStandard(projectId: Long,value: Standard)

    @Update(entity = ProjectEntity::class)
    fun softDelete(obj: ProjectSoftDelete)

    @Query("UPDATE projects SET twoPhaseVoltageInVolt = :value WHERE id = :projectId")
    suspend fun updateTwoPhaseVoltage(projectId: Long, value: Double)

    @Query("UPDATE projects SET two_phase_powerfactor_value = :value WHERE id = :projectId")
    suspend fun updateTwoPhasePowerFactor(projectId: Long, value: Double)

    @Entity
    data class ProjectSoftDelete( @ColumnInfo(name = "id") val id:Long,val isDeleted:Boolean)




}