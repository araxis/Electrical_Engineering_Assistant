package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId

class UpdatePanelFeedInsulation(val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: Insulation){
        repository.updateInsulation(relationId,value)
    }
}