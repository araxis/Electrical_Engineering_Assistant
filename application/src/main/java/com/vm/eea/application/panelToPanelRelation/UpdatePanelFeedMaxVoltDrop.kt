package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop

class UpdatePanelFeedMaxVoltDrop(val repository: IPanelToPanelRelationRepository) {
    suspend operator fun invoke(relationId: RelationId, value: VoltDrop){
        repository.updateVoltDrop(relationId,value)
    }
}