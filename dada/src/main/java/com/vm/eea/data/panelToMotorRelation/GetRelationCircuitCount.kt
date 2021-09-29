package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetRelationCircuitCount
import com.vm.eea.data.AppDatabase

class GetRelationCircuitCount(private val db:AppDatabase):IGetRelationCircuitCount {

    override suspend fun invoke(relationId: RelationId): List<SelectableItem<CircuitCount>> {
        val defaultsCircuitCounts= listOf(1,2,3,4,5,6,7,8,9,12,16,20)
        val relationCircuitCount=db.panelToMotorReadDao().getCircuitCount(relationId.id)
        return defaultsCircuitCounts.map {
            SelectableItem(CircuitCount(it),isSelected = it==relationCircuitCount.value)
        }
    }
}