package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.*
import com.vm.eea.application.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.application.panelToPanelRelation.PanelToPanelRelation
import com.vm.eea.data.AppDatabase

class PanelToPanelRelationRepository(private val db: AppDatabase): IPanelToPanelRelationRepository {



    override suspend fun add(relation: PanelToPanelRelation) {
        db.panelToPanelRelationDao().insert(toEntity(relation))
    }

    override suspend fun updateSourceByConsumerId(consumerId: PanelId, newFeederId: PanelId) {
        db.panelToPanelRelationDao().updateSource(consumerId.id,newFeederId.id)
    }

    override suspend fun updateLength(relationId: RelationId, length: Length) {
        db.panelToPanelRelationDao().updateLength(relationId.id,length.value,length.unit)
    }

    override suspend fun updateMethodOfInstallation(relationId: RelationId, value: MethodOfInstallation) {
       db.panelToPanelRelationDao().updateMethodOfInstallation(relationId.id,value)
    }

    override suspend fun updateVoltDrop(relationId: RelationId, value: VoltDrop) {
        db.panelToPanelRelationDao().updateVoltDrop(relationId.id,value.value)
    }

    override suspend fun updateAmbientTemperature(relationId: RelationId, value: Temperature) {
        db.panelToPanelRelationDao().updateAmbientTemperature(relationId.id,value.value,value.unit)
    }

    override suspend fun updateGroundTemperature(relationId: RelationId, value: Temperature) {
        db.panelToPanelRelationDao().updateGroundTemperature(relationId.id,value.value,value.unit)
    }

    override suspend fun updateSoilThermalResistivity(relationId: RelationId, value: ThermalResistivity) {
        db.panelToPanelRelationDao().updateSoilThermalResistivity(relationId.id,value.value,value.unit)
    }

    override suspend fun updateConductor(relationId: RelationId, value: Conductor) {
        db.panelToPanelRelationDao().updateConductor(relationId.id,value)
    }

    override suspend fun updateInsulation(relationId: RelationId, value: Insulation) {
        db.panelToPanelRelationDao().updateInsulation(relationId.id,value)
    }

    override suspend fun updateCircuitCount(relationId: RelationId, value: CircuitCount) {
        db.panelToPanelRelationDao().updateCircuitCount(relationId.id,value.value)
    }


    private fun toEntity(relation: PanelToPanelRelation): PanelToPanelRelationEntity {
       return with(relation){ PanelToPanelRelationEntity(

      length = length,
        methodOfInstallation = methodOfInstallation,
        insulation = insulation,
        conductor = conductor,
        ambientTemperature = ambientTemperature,
        groundTemperature = groundTemperature,
        id =0 ,
        fromPanelId = fromPanel.id,
        maxVoltageDrop = maxVoltageDrop,
        soilThermalResistivityUnit = soilThermalResistivity,
        circuitCount=circuitCount,
        toPanelId = toPanelId.id
    )
    }
    }
}