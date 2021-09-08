package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.Length
import com.vm.eea.domain.RelationId

class UpdateMotorFeedLineLength(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,length: Length){
        repository.updateLength(relationId,length)
    }

}