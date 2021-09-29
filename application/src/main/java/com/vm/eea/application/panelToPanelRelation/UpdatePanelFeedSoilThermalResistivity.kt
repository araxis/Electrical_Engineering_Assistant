package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.ThermalResistivity

class UpdatePanelFeedSoilThermalResistivity(val repository: IPanelToPanelRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: ThermalResistivity){

        repository.updateSoilThermalResistivity(relationId,value)
    }
}