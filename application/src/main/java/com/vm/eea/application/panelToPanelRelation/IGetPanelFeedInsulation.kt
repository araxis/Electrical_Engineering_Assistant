package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetPanelFeedInsulation {
   suspend  operator fun invoke(relationId: RelationId): List<SelectableItem<Insulation>>
}