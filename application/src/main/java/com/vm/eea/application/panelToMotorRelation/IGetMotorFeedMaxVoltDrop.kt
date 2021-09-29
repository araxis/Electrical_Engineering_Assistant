package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.VoltDrop

interface IGetMotorFeedMaxVoltDrop {

    suspend operator fun invoke(relationId:RelationId):VoltDrop
}