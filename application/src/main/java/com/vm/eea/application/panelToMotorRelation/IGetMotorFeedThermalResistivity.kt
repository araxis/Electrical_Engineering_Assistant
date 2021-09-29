package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity

interface IGetMotorFeedThermalResistivity {

    suspend operator fun invoke(relationId: RelationId):List<SelectableItem<ThermalResistivity>>
}