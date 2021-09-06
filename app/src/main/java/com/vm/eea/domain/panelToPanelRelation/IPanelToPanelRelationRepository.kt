package com.vm.eea.domain.panelToPanelRelation

import com.vm.eea.domain.*
import com.vm.eea.domain.panel.Panel
import kotlinx.coroutines.flow.Flow

interface IPanelToPanelRelationRepository {

    suspend fun add(relation:PanelToPanelRelation)
    suspend fun getFeederRelationByConsumerId(panelId: Long): Flow<PanelToPanelRelation>
    suspend fun getFeederRelationById(relationId: Long): Flow<PanelToPanelRelation>
    suspend fun updateSourceFeeder(toPanel:Panel, newSource: Panel)
    suspend fun updateLength(relationId:Long,length:Length)
    suspend fun updateMethodOfInstallation(relationId:Long,value:MethodOfInstallation)
    suspend fun updateVoltDrop(relationId: Long, value: VoltDrop)
    suspend fun updateAmbientTemperature(relationId: Long, value: Temperature)
    suspend fun updateGroundTemperature(relationId: Long, value: Temperature)
    suspend fun updateSoilThermalResistivity(relationId: Long, value: ThermalResistivity)
    suspend fun updateConductor(relationId: Long, value: Conductor)
    suspend fun updateInsulation(relationId: Long, value: Insulation)
    suspend fun updateCircuitCount(relationId: Long, value: CircuitCount)
}