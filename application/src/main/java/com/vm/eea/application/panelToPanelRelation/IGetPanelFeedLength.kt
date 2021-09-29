package com.vm.eea.application.panelToPanelRelation

import com.vm.eea.application.Length
import com.vm.eea.application.RelationId

interface IGetPanelFeedLength {

    suspend  operator fun invoke(relationId:RelationId):Length
}