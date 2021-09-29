package com.vm.eea.application.panelToMotorRelation

import com.vm.eea.application.RelationId
import com.vm.eea.application.Temperature
import com.vm.eea.application.Environment

interface IGetMotorFeedTemperature {

    suspend operator fun invoke(relationId: RelationId, environment: Environment):Temperature
}