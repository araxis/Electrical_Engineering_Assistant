package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.CircuitCount
import com.vm.eea.domain.RelationId

class UpdateMotorRelationCircuitCount(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:CircuitCount){
        repository.updateCircuitCount(relationId,value)
    }
}