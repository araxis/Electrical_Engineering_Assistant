package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Environment
import com.vm.eea.application.RelationId
import com.vm.eea.application.Temperature

class UpdatePanelFeedTemperature(val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: Temperature,environment: Environment){

        when(environment){
            Environment.Ambient -> repository.updateAmbientTemperature(relationId,value)
            Environment.Ground -> repository.updateGroundTemperature(relationId,value)
        }

    }
}