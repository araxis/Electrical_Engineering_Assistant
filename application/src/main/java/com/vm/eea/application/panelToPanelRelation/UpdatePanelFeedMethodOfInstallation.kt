package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId

class UpdatePanelFeedMethodOfInstallation(val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: MethodOfInstallation){
        repository.updateMethodOfInstallation(relationId,value)
    }
}