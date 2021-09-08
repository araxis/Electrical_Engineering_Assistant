package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.RelationId
import com.vm.eea.domain.Temperature

class UpdateMotorRelationAmbientTemperature(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, temperature: Temperature){
        repository.updateAmbientTemperature(relationId,temperature)
    }
}