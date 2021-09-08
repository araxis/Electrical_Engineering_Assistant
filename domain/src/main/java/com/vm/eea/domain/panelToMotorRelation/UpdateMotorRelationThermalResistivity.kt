package com.vm.eea.domain.panelToMotorRelation

import com.vm.eea.domain.RelationId
import com.vm.eea.domain.ThermalResistivity

class UpdateMotorRelationThermalResistivity(private val repository: IPanelToMotorRelationRepository) {

    suspend operator fun invoke(relationId: RelationId,value:ThermalResistivity){
        repository.updateSoilThermalResistivity(relationId,value)
    }
}