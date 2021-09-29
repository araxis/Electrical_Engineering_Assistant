package com.vm.eea.data.panelToMotorRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedInsulation
import com.vm.eea.data.AppDatabase

class GetMotorFeedInsulation(private val db:AppDatabase): IGetMotorFeedInsulation {

    override suspend operator fun invoke(relationId: RelationId):List<SelectableItem<Insulation>> {
        val current=db.panelToMotorReadDao().getInsulation(relationId.id)
        return Insulation.values().map { SelectableItem(it,isSelected = it==current) }
    }
}