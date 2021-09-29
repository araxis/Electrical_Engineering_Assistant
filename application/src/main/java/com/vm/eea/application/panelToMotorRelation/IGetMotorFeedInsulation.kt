package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetMotorFeedInsulation {
   suspend  operator fun invoke(relationId: RelationId): List<SelectableItem<Insulation>>
}