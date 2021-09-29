package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.PanelId
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelRelationCircuitCount
import com.vm.eea.data.AppDatabase

class GetPanelRelationCircuitCount(private val db:AppDatabase):IGetPanelRelationCircuitCount {

    override suspend fun invoke(relationId: RelationId): List<SelectableItem<CircuitCount>> {
        val defaultsCircuitCounts= listOf(1,2,3,4,5,6,7,8,9,12,16,20)
        val relationCircuitCount=db.panelToPanelReadDao().getCircuitCount(relationId.id)
        return defaultsCircuitCounts.map {
            SelectableItem(CircuitCount(it),isSelected = it==relationCircuitCount)
        }
    }
}