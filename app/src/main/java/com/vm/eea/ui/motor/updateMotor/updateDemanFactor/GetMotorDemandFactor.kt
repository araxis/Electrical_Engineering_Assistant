package com.vm.eea.ui.motor.updateMotor.updateDemanFactor

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.LoadId
import kotlinx.coroutines.flow.map

class GetMotorDemandFactor(private val repository: IMotorRepository) {

    operator fun invoke(motorId: LoadId) =repository.getMotorFlow(motorId)
        .map { MotorDemandFactor(it.id,it.code,it.demandFactor) }
}