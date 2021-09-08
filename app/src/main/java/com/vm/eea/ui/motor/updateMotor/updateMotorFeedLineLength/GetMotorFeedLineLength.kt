package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength

import com.vm.eea.domain.Length
import com.vm.eea.domain.RelationId
import com.vm.eea.domain.panelToMotorRelation.IPanelToMotorRelationRepository
import kotlinx.coroutines.flow.map

class GetMotorFeedLineLength(private val repository: IPanelToMotorRelationRepository) {
    operator fun invoke(relationId: RelationId)=repository.getFeederRelationById(relationId)
                .map { MotorFeedLength(it.id,it.length) }



    data class MotorFeedLength(val relationId: RelationId,val length:Length)
}