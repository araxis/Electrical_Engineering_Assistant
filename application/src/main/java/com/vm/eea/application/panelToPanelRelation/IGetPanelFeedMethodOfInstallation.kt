package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.MethodOfInstallation
import com.vm.eea.application.RelationId
import com.vm.eea.application.SelectableItem

interface IGetPanelFeedMethodOfInstallation {
   suspend  operator fun invoke(relationId: RelationId): List<SelectableItem<MethodOfInstallation>>
}