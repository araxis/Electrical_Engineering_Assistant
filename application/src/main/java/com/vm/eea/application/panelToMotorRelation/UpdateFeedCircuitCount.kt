package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId

class UpdateFeedCircuitCount(val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: CircuitCount){
        repository.updateCircuitCount(relationId, value)
    }
}