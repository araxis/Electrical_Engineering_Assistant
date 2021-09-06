package com.vm.eea.data.repositories

import androidx.room.withTransaction
import com.vm.eea.data.AppDatabase
import com.vm.eea.data.model.PanelToPanelRelationEntity
import com.vm.eea.domain.*
import com.vm.eea.domain.panel.Panel
import com.vm.eea.domain.panelToPanelRelation.IPanelToPanelRelationRepository
import com.vm.eea.domain.panelToPanelRelation.PanelToPanelRelation
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge

@FlowPreview
class PanelToPanelRelationRepository(private val db:AppDatabase): IPanelToPanelRelationRepository {



    override suspend fun add(relation: PanelToPanelRelation) {
        db.panelToPanelRelationDao().insert(toEntity(relation))
    }


    override suspend fun getFeederRelationByConsumerId(panelId:Long): Flow<PanelToPanelRelation>{
      return  db.withTransaction {
            db.panelToPanelRelationDao().getFeederRelationByConsumerId(panelId).filterNotNull().flatMapMerge {
               db.panelDao().getPanelFlow(it.fromPanelId).combine(db.panelDao().getPanelFlow(it.toPanelId)){
                   from,to->
                   it.toDomain(from.toDomain(),to.toDomain())
               }
            }
        }

    }

    override suspend fun getFeederRelationById(relationId: Long): Flow<PanelToPanelRelation> {
        return  db.withTransaction {
            db.panelToPanelRelationDao().getFeederRelationById(relationId).flatMapMerge {
                db.panelDao().getPanelFlow(it.fromPanelId).combine(db.panelDao().getPanelFlow(it.toPanelId)){
                        from,to->
                    it.toDomain(from.toDomain(),to.toDomain())
                }
            }
        }
    }

    override suspend fun updateSourceFeeder(toPanel:Panel, newSource:Panel){
        db.panelToPanelRelationDao().updateSource(toPanel.id,newSource.id)
    }

    override suspend fun updateLength(relationId: Long, length: Length) {
        db.panelToPanelRelationDao().updateLength(relationId,length.value,length.unit)
    }

    override suspend fun updateMethodOfInstallation(relationId: Long, value: MethodOfInstallation) {
       db.panelToPanelRelationDao().updateMethodOfInstallation(relationId,value)
    }

    override suspend fun updateVoltDrop(relationId: Long, value: VoltDrop) {
        db.panelToPanelRelationDao().updateVoltDrop(relationId,value.value)
    }

    override suspend fun updateAmbientTemperature(relationId: Long, value: Temperature) {
        db.panelToPanelRelationDao().updateAmbientTemperature(relationId,value.value,value.unit)
    }

    override suspend fun updateGroundTemperature(relationId: Long, value: Temperature) {
        db.panelToPanelRelationDao().updateGroundTemperature(relationId,value.value,value.unit)
    }

    override suspend fun updateSoilThermalResistivity(relationId: Long, value: ThermalResistivity) {
        db.panelToPanelRelationDao().updateSoilThermalResistivity(relationId,value.value,value.unit)
    }

    override suspend fun updateConductor(relationId: Long, value: Conductor) {
        db.panelToPanelRelationDao().updateConductor(relationId,value)
    }

    override suspend fun updateInsulation(relationId: Long, value: Insulation) {
        db.panelToPanelRelationDao().updateInsulation(relationId,value)
    }

    override suspend fun updateCircuitCount(relationId: Long, value: CircuitCount) {
        db.panelToPanelRelationDao().updateCircuitCount(relationId,value.value)
    }

    private fun toEntity(relation: PanelToPanelRelation):PanelToPanelRelationEntity {
       return with(relation){ PanelToPanelRelationEntity(

      length = length,
        methodOfInstallation = methodOfInstallation,
        insulation = insulation,
        conductor = conductor,
        ambientTemperature = ambientTemperature,
        groundTemperature = groundTemperature,
        id =id.id ,
        fromPanelId = fromPanel.id,
        maxVoltageDrop = maxVoltageDrop,
        soilThermalResistivityUnit = soilThermalResistivity,
        circuitCount=circuitCount,
        toPanelId = toPanelId.id
    )
    }
    }
}