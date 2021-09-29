package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.ThermalResistivity

interface IGetPanelFeedThermalResistivity {

    suspend operator fun invoke(relationId: RelationId):List<SelectableItem<ThermalResistivity>>
}