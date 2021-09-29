package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId

class UpdatePanelFeedLength(private val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, length: Length){
        repository.updateLength(relationId,length)
    }
}