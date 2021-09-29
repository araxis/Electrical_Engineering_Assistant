package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetPanelFeedConductor {
    suspend operator fun invoke(relationId: RelationId):List<SelectableItem<Conductor>>
}