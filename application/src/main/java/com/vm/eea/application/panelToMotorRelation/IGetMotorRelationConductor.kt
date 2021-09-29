package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetMotorRelationConductor {
    suspend operator fun invoke(relationId: RelationId): List<SelectableItem<Conductor>>
}