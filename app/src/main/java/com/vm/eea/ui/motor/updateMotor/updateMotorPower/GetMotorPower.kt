package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.LoadId
import kotlinx.coroutines.flow.map

class GetMotorPowerDetails(private val repository: IMotorRepository) {

    operator fun invoke(motorId: LoadId) =repository
        .getMotorFlow(motorId)
        .map { MotorPowerDetails(it.id,it.code,it.power) }
}