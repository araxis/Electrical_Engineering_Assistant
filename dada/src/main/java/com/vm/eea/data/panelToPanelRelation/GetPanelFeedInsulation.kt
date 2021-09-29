package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.Insulation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToMotorRelation.IGetMotorFeedInsulation
import com.vm.eea.data.AppDatabase

class GetPanelFeedInsulation(private val db:AppDatabase): IGetMotorFeedInsulation {

    override suspend operator fun invoke(relationId: RelationId):List<SelectableItem<Insulation>> {
        val current=db.panelToPanelReadDao().getInsulation(relationId.id)
        return Insulation.values().map { SelectableItem(it,isSelected = it==current) }
    }
}