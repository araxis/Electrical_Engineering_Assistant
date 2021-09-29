package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedConductor
import com.vm.eea.data.AppDatabase

class GetPanelFeedConductor(private val db: AppDatabase) : IGetPanelFeedConductor {

    override suspend operator fun invoke(relationId: RelationId):List<SelectableItem<Conductor>> {
       val current= db.panelToPanelReadDao().getConductor(relationId.id)
        return Conductor.values().map { SelectableItem(it,isSelected = it==current) }

    }

}