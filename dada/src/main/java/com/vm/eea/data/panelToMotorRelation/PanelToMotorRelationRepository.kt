package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.*
import com.vm.eea.application.motor.MotorId
import com.vm.eea.application.panelToMotorRelation.IPanelToMotorRelationRepository
import com.vm.eea.application.panelToMotorRelation.PanelToMotorRelation
import com.vm.eea.data.AppDatabase

class PanelToMotorRelationRepository(private val db: AppDatabase): IPanelToMotorRelationRepository {
    override suspend fun add(relation: PanelToMotorRelation) {
        db.panelToMotorRelationDao().insert(toEntity(relation))
    }

    override suspend fun getByConsumer(consumerId: MotorId):PanelToMotorRelation {
       val entity= db.panelToMotorRelationDao().getByConsumer(consumerId.id)
        return with(entity){
            PanelToMotorRelation(
                fromPanel = PanelId(fromPanelId),
                toMotorId = MotorId(toLoadId),
                length=length,
                maxVoltageDrop=maxVoltageDrop,
                methodOfInstallation=methodOfInstallation,
                ambientTemperature=ambientTemperature,
                groundTemperature=groundTemperature,
                soilThermalResistivity=soilThermalResistivityUnit,
                conductor=conductor,
                insulation=insulation,
                circuitCount=circuitCount

            )
        }
    }

    override suspend fun updateSourceByConsumerId(consumerId: MotorId, newFeeder: PanelId) {
        db.panelToMotorRelationDao().updateSource(consumerId.id,newFeeder.id)
    }


    override suspend fun deleteRelationByLoadId(loadId: MotorId){
        db.panelToMotorRelationDao().deleteRelationByLoadId(loadId.id)
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
                id = 0,
                fromPanelId = fromPanel.id,
                maxVoltageDrop = maxVoltageDrop,
                soilThermalResistivityUnit = soilThermalResistivity,
                circuitCount = circuitCount,
                toLoadId = toMotorId.id
            )
        }

    }
}