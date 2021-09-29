package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId

class UpdateMotorFeedConductor(val repository: IPanelToMotorRelationRepository) {
    suspend operator fun invoke(relationId: RelationId, value: Conductor){
        repository.updateConductor(relationId, value)
    }
}