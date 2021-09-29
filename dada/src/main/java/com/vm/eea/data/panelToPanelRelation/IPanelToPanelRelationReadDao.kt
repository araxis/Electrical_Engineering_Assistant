package com.vm.eea.data.panelToPanelRelation

import androidx.room.Dao
import androidx.room.Query
import com.vm.eea.application.*

@Dao
interface IPanelToPanelRelationReadDao {

    @Query("select circuit_count_value from panel_panel_relations where id=:relationId")
    suspend fun getCircuitCount(relationId:Long):Int

    @Query("select conductor from panel_panel_relations where id=:relationId")
    suspend fun getConductor(relationId: Long): Conductor

    @Query("select insulation from panel_panel_relations where id=:relationId")
    suspend fun getInsulation(relationId: Long): Insulation

    @Query("select length_value as value,length_unit as unit from panel_panel_relations where id=:relationId")
    suspend fun getLength(relationId: Long): Length

    @Query("select methodOfInstallation from panel_panel_relations where id=:relationId")
    suspend fun getMethodOfInstallation(relationId: Long): MethodOfInstallation

    @Query("select soil_thermal_resistivity_value as value,soil_thermal_resistivity_unit as unit from panel_panel_relations where id=:relationId")
    suspend fun getSoilResistivity(relationId: Long): ThermalResistivity


    @Query("select ambient_temp_value as value,ambient_temp_unit as unit from panel_panel_relations where id=:relationId")
    suspend fun getAmbientTemperature(relationId: Long): Temperature

    @Query("select ground_temp_value as value,ground_temp_unit as unit from panel_panel_relations where id=:relationId")
    suspend fun getGroundTemperature(relationId: Long): Temperature

    @Query("select volt_drop_value as value from panel_panel_relations where id=:relationId")
    suspend fun getMaxVoltDrop(relationId: Long): VoltDrop

}