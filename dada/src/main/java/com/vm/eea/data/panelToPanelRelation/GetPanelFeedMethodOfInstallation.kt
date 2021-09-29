package com.vm.eea.data.panelToPanelRelation

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem
import com.vm.eea.application.panelToPanelRelation.IGetPanelFeedMethodOfInstallation
import com.vm.eea.data.AppDatabase

class GetPanelFeedMethodOfInstallation(private val db:AppDatabase): IGetPanelFeedMethodOfInstallation {
    override suspend fun invoke(relationId: RelationId): List<SelectableItem<MethodOfInstallation>> {
        val current=db.panelToPanelReadDao().getMethodOfInstallation(relationId.id)
        return MethodOfInstallation.values().map { SelectableItem(it,isSelected = it==current) }
    }
}