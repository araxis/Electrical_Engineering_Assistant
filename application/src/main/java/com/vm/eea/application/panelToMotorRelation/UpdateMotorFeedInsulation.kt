package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId

class UpdateMotorFeedInsulation(val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: Insulation){
        repository.updateInsulation(relationId,value)
    }
}