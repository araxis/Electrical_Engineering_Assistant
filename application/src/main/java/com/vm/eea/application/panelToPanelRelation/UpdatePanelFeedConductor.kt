package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId

class UpdatePanelFeedConductor(val repository: IPanelToPanelRelationRepository) {
    suspend operator fun invoke(relationId: RelationId, value: Conductor){
        repository.updateConductor(relationId, value)
    }
}