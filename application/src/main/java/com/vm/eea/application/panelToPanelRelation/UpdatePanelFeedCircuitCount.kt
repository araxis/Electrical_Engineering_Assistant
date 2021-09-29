package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId

class UpdatePanelFeedCircuitCount(val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: CircuitCount){
        repository.updateCircuitCount(relationId, value)
    }
}