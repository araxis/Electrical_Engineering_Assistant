package com.vm.eea.data.panelToPanelRelation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vm.eea.application.*
import com.vm.eea.data.model.PanelToPanelRelationEntity

@Dao
interface IPanelToPanelRelationDao {


    @Insert
    fun insert(item:PanelToPanelRelationEntity)



    @Query("UPDATE panel_panel_relations SET fromPanelId = :newSourceId WHERE toPanelId = :toPanelId")
    suspend fun updateSource(toPanelId:Long,newSourceId:Long)

    @Query("UPDATE panel_panel_relations SET length_value = :value,length_unit= :unit WHERE id = :relationId")
    suspend fun updateLength(relationId:Long,value:Double,unit: Length.Unit)

    @Query("UPDATE panel_panel_relations SET methodOfInstallation = :value WHERE id = :relationId")
    suspend fun updateMethodOfInstallation(relationId:Long,value: MethodOfInstallation)

    @Query("UPDATE panel_panel_relations SET volt_drop_value = :value WHERE id = :relationId")
    suspend fun updateVoltDrop(relationId: Long, value: Double)

    @Query("UPDATE panel_panel_relations SET ambient_temp_value = :value,ambient_temp_unit= :unit  WHERE id = :relationId")
    suspend fun updateAmbientTemperature(relationId: Long, value: Double, unit: UnitOfTemperature)

    @Query("UPDATE panel_panel_relations SET ground_temp_value = :value,ground_temp_unit= :unit  WHERE id = :relationId")
    suspend fun updateGroundTemperature(relationId: Long, value: Double, unit: UnitOfTemperature)

    @Query("UPDATE panel_panel_relations SET soil_thermal_resistivity_value = :value,soil_thermal_resistivity_unit= :unit  WHERE id = :relationId")
    fun updateSoilThermalResistivity(relationId: Long, value: Double, unit: UnitOfThermalResistivity)
    @Query("UPDATE panel_panel_relations SET conductor = :value WHERE id = :relationId")
    fun updateConductor(relationId: Long, value: Conductor)

    @Query("UPDATE panel_panel_relations SET insulation = :value WHERE id = :relationId")
    fun updateInsulation(relationId: Long, value: Insulation)

    @Query("UPDATE panel_panel_relations SET circuit_count_value = :value WHERE id = :relationId")
    fun updateCircuitCount(relationId: Long, value: Int)
}

