package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import com.vm.eea.domain.LoadId
import com.vm.eea.ui.models.GetSimpleMotor
import com.vm.eea.ui.models.GetSimplePanels
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class GetMotorFeederDetails(
    private val getSimpleMotor: GetSimpleMotor,
    private val getSimplePanels: GetSimplePanels,
) {

    operator fun invoke(motorId: LoadId)= getSimpleMotor(motorId).flatMapMerge { motor->
        getSimplePanels(motor.projectId.id).map { MotorFeederDetails(motor,it) }

    }

}