package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.Insulation
import com.vm.eea.domain.RelationId

class UpdateMotorRelationInsulation(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:Insulation){
        repository.updateInsulation(relationId,value)
    }

}