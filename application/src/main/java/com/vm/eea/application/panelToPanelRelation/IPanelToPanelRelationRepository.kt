package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.*

interface IPanelToPanelRelationRepository {

    suspend fun add(relation: PanelToPanelRelation)
    suspend fun updateSourceByConsumerId(consumerId:PanelId, newFeederId: PanelId)
    suspend fun updateLength(relationId: RelationId, length: Length)
    suspend fun updateMethodOfInstallation(relationId: RelationId, value: MethodOfInstallation)
    suspend fun updateVoltDrop(relationId: RelationId, value: VoltDrop)
    suspend fun updateAmbientTemperature(relationId: RelationId, value: Temperature)
    suspend fun updateGroundTemperature(relationId: RelationId, value: Temperature)
    suspend fun updateSoilThermalResistivity(relationId: RelationId, value: ThermalResistivity)
    suspend fun updateConductor(relationId: RelationId, value: Conductor)
    suspend fun updateInsulation(relationId: RelationId, value: Insulation)
    suspend fun updateCircuitCount(relationId: RelationId, value: CircuitCount)
}