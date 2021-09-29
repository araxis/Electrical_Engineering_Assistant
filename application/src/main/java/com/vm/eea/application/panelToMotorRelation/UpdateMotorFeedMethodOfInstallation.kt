package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId

class UpdateMotorFeedMethodOfInstallation(val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: MethodOfInstallation){
        repository.updateMethodOfInstallation(relationId,value)
    }
}