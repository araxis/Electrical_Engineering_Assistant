package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.Conductor
import com.vm.eea.domain.MethodOfInstallation
import com.vm.eea.domain.RelationId

class UpdateMotorRelationConductor(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:Conductor){
        repository.updateConductor(relationId,value)
    }

}