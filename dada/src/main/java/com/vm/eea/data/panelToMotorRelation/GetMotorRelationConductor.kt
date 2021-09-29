package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.Conductor
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetMotorRelationConductor
import com.vm.eea.data.AppDatabase

class GetMotorRelationConductor(private val db: AppDatabase) : IGetMotorRelationConductor {

    override suspend operator fun invoke(relationId: RelationId):List<SelectableItem<Conductor>> {
       val current= db.panelToMotorReadDao().getConductor(relationId.id)
        return Conductor.values().map { SelectableItem(it,isSelected = it==current) }

    }

}