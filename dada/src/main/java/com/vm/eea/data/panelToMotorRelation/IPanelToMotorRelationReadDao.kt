package com.vm.eea.data.panelToMotorRelation

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.application.*

@Dao
interface IPanelToMotorRelationReadDao {

    @Query("select length_value as value,length_unit as unit from panel_motor_relations where id=:relationId")
    suspend fun getLength(relationId:Long):Length

    @Query("select circuit_count_value as value from panel_motor_relations where id=:relationId")
    suspend fun getCircuitCount(relationId: Long): CircuitCount

    @Query("select conductor as value from panel_motor_relations where id=:relationId")
    suspend fun getConductor(relationId: Long): Conductor

    @Query("select insulation as value from panel_motor_relations where id=:relationId")
    suspend fun getInsulation(relationId: Long): Insulation

    @Query("select volt_drop_value as value from panel_motor_relations where id=:relationId")
    suspend fun getMaxVoltDrop(relationId: Long): VoltDrop

    @Query("select soil_thermal_resistivity_value as value,soil_thermal_resistivity_unit as unit from panel_motor_relations where id=:relationId")
    suspend fun getSoilResistivity(relationId: Long): ThermalResistivity

    @Query("select ambient_temp_value as value,ambient_temp_unit as unit from panel_motor_relations where id=:relationId")
    suspend fun getAmbientTemperature(relationId: Long): Temperature

    @Query("select ground_temp_value as value,ground_temp_unit as unit from panel_motor_relations where id=:relationId")
    suspend fun getGroundTemperature(relationId: Long): Temperature

    @Query("select methodOfInstallation as value from panel_motor_relations where id=:relationId")
    fun getMethodOfInstallation(relationId: Long): MethodOfInstallation


}