package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.domain.RelationId

class UpdateMotorRelationMethodOfInstallation(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:MethodOfInstallation){
        repository.updateMethodOfInstallation(relationId,value)
    }

}