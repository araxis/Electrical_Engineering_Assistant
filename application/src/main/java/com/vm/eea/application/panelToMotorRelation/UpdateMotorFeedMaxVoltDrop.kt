package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop

class UpdateMotorFeedMaxVoltDrop(val repository: IPanelToMotorRelationRepository) {
    suspend operator fun invoke(relationId: RelationId, value: VoltDrop){
        repository.updateVoltDrop(relationId,value)
    }
}