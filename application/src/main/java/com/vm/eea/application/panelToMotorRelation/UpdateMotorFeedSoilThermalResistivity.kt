package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.ThermalResistivity

class UpdateMotorFeedSoilThermalResistivity(val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId, value: ThermalResistivity){

        repository.updateSoilThermalResistivity(relationId,value)
    }
}