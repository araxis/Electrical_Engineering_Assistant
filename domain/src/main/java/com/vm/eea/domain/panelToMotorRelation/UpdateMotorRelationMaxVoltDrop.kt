package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.RelationId
import com.vm.eea.domain.VoltDrop

class UpdateMotorRelationMaxVoltDrop(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:VoltDrop){
        repository.updateVoltDrop(relationId,value)
    }
}