package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop

interface IGetPanelFeedMaxVoltDrop {

    suspend operator fun invoke(relationId:RelationId):VoltDrop
}