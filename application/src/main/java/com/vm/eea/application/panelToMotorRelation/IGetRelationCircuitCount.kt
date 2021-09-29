package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetRelationCircuitCount {

    suspend operator fun invoke(relationId:RelationId):List<SelectableItem<CircuitCount>>
}