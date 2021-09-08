package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop

import com.vm.eea.domain.RelationId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import kotlinx.coroutines.flow.map

class GetMotorRelationMaxVoltDrop(private val repository: IPanelToMotorRelationRepository) {

    operator fun invoke(relationId: RelationId)=repository
        .getFeederRelationById(relationId)
        .map { it.maxVoltageDrop }
}