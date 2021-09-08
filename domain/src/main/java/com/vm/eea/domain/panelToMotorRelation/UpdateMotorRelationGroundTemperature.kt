package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.RelationId
import com.vm.eea.domain.Temperature

class UpdateMotorRelationGroundTemperature(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,temperature: Temperature){
        repository.updateGroundTemperature(relationId,temperature)
    }
}