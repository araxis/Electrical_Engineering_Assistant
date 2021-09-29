package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId

interface IPanelToMotorRelationRepository {

    suspend fun add(relation: PanelToMotorRelation)
    suspend fun updateSourceByConsumerId(consumerId: MotorId, newFeeder: PanelId)
    suspend fun updateLength(relationId:RelationId,length: Length)
    suspend fun updateCircuitCount(relationId: RelationId, value: CircuitCount)
    suspend fun updateConductor(relationId: RelationId, value: Conductor)
    suspend fun updateInsulation(relationId: RelationId, value: Insulation)
    suspend fun updateVoltDrop(relationId: RelationId, value: VoltDrop)
    suspend fun updateMethodOfInstallation(relationId: RelationId, value: MethodOfInstallation)
    suspend fun updateSoilThermalResistivity(relationId: RelationId, value: ThermalResistivity)
    suspend fun updateAmbientTemperature(relationId: RelationId, value: Temperature)
    suspend fun updateGroundTemperature(relationId: RelationId, value: Temperature)
}