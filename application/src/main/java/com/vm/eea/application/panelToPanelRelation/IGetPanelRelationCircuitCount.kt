package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetPanelRelationCircuitCount {

    suspend operator fun invoke(relationId:RelationId):List<SelectableItem<CircuitCount>>

}