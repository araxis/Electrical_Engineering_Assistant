package com.vm.eea.data.repositories

import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.PanelToMotorRelationEntity
import com.vm.eea.domain.*
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.PanelId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.domain.panelToMotorRelation.PanelToMotorRelation
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PanelToMotorRelationRepository(private val db: AppDatabase):IPanelToMotorRelationRepository {
    override suspend fun add(relation: PanelToMotorRelation) {
        db.panelToMotorRelationDao().insert(toEntity(relation))
    }

    override  fun getFeederRelationByConsumerId(loadId: LoadId): Flow<PanelToMotorRelation> {
        return db.panelToMotorRelationDao().getFeederRelationByConsumerId(loadId.id)
            .map { it.toDomain() }


    }

    override  fun getFeederRelationById(relationId: RelationId): Flow<PanelToMotorRelation> {
        return db.panelToMotorRelationDao().getFeederRelationById(relationId.id)
            .map { it.toDomain() }
    }

    override suspend fun updateSourceFeeder(loadId: LoadId, newFeeder: PanelId) {
        db.panelToMotorRelationDao().updateSource(loadId.id,newFeeder.id)
    }

    override suspend fun updateLength(relationId: RelationId, length: Length) {
        db.panelToMotorRelationDao().updateLength(relationId.id,length.value,length.unit)
    }

    override suspend fun updateMethodOfInstallation(
        relationId: RelationId,
        value: MethodOfInstallation,
    ) {
        db.panelToMotorRelationDao().updateMethodOfInstallation(relationId.id,value)
    }

    override suspend fun updateVoltDrop(relationId: RelationId, value: VoltDrop) {
        db.panelToMotorRelationDao().updateVoltDrop(relationId.id,value.value)
    }

    override suspend fun updateAmbientTemperature(relationId: RelationId, value: Temperature) {
        db.panelToMotorRelationDao().updateAmbientTemperature(relationId.id,value.value,value.unit)
    }

    override suspend fun updateGroundTemperature(relationId: RelationId, value: Temperature) {
        db.panelToMotorRelationDao().updateGroundTemperature(relationId.id,value.value,value.unit)
    }

    override suspend fun updateSoilThermalResistivity(
        relationId: RelationId,
        value: ThermalResistivity,
    ) {
        db.panelToMotorRelationDao().updateSoilThermalResistivity(relationId.id,value.value,value.unit)
    }

    override suspend fun updateConductor(relationId: RelationId, value: Conductor) {
       db.panelToMotorRelationDao().updateConductor(relationId.id,value)
    }

    override suspend fun updateInsulation(relationId: RelationId, value: Insulation) {
        db.panelToMotorRelationDao().updateInsulation(relationId.id,value)
    }

    override suspend fun updateCircuitCount(relationId: RelationId, value: CircuitCount) {
        db.panelToMotorRelationDao().updateCircuitCount(relationId.id,value.value)
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
                id = id.id,
                fromPanelId = fromPanelId.id,
                maxVoltageDrop = maxVoltageDrop,
                soilThermalResistivityUnit = soilThermalResistivity,
                circuitCount = circuitCount,
                toLoadId = toLoadId.id
            )
        }

    }
}