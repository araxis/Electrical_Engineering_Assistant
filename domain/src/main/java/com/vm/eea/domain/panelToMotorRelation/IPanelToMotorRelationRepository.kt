package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.*
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.PanelId
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation
import kotlinx.coroutines.flow.Flow

interface IPanelToMotorRelationRepository {

    suspend fun add(relation:PanelToMotorRelation)

    fun getFeederRelationByConsumerId(loadId: LoadId): Flow<PanelToMotorRelation>
    fun getFeederRelationById(relationId: RelationId): Flow<PanelToMotorRelation>
    suspend fun updateSourceFeeder(loadId: LoadId, newFeeder: PanelId)
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