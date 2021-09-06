package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.PanelToMotorRelationEntity
import com.vm.eea.domain.*
import com.vm.eea.domain.load.LoadId
import com.vm.eea.domain.panel.PanelId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.domain.panelToMotorRelation.PanelToMotorRelation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PanelToMotorRelationRepository(private val db:AppDatabase):IPanelToMotorRelationRepository {
    override suspend fun add(relation: PanelToMotorRelation) {
        db.panelToMotorRelationDao().insert(toEntity(relation))
    }

    override  fun getFeederRelationByConsumerId(loadId: LoadId): Flow<PanelToMotorRelation> {
        return db.panelToMotorRelationDao().getFeederRelationByConsumerId(loadId.id)
            .map { it.toDomain() }


    }

    override suspend fun updateSourceFeeder(loadId: LoadId, newFeeder: PanelId) {
        db.panelToMotorRelationDao().updateSource(loadId.id,newFeeder.id)
    }

    override suspend fun updateLength(relationId: RelationId, length: Length) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMethodOfInstallation(
        relationId: RelationId,
        value: MethodOfInstallation,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateVoltDrop(relationId: RelationId, value: VoltDrop) {
        TODO("Not yet implemented")
    }

    override suspend fun updateAmbientTemperature(relationId: RelationId, value: Temperature) {
        TODO("Not yet implemented")
    }

    override suspend fun updateGroundTemperature(relationId: RelationId, value: Temperature) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSoilThermalResistivity(
        relationId: RelationId,
        value: ThermalResistivity,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateConductor(relationId: RelationId, value: Conductor) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInsulation(relationId: RelationId, value: Insulation) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCircuitCount(relationId: RelationId, value: CircuitCount) {
        TODO("Not yet implemented")
    }


    private fun toEntity(relation: PanelToMotorRelation): PanelToMotorRelationEntity {
        return with(relation) {
            PanelToMotorRelationEntity(

                length = length,
                methodOfInstallation = methodOfInstallation,
                insulation = insulation,
                conductor = conductor,
                ambientTemperature = ambientTemperature,
                groundTemperature = groundTemperature,
                id = id,
                fromPanelId = fromPanelId.id,
                maxVoltageDrop = maxVoltageDrop,
                soilThermalResistivityUnit = soilThermalResistivity,
                circuitCount = circuitCount,
                toLoadId = toLoadId.id
            )
        }

    }
}