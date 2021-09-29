package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId

class UpdateMotorFeedLength(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,length:Length) =repository.updateLength(relationId,length)

}