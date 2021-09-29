package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.Temperature
import com.vm.eea.application.Environment

interface IGetPanelFeedTemperature {

    suspend operator fun invoke(relationId: RelationId, environment: Environment):Temperature
}