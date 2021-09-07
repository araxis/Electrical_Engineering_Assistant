package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.LoadId
import kotlinx.coroutines.flow.map

class GetMotorPowerfactorDetail(private val repository: IMotorRepository) {

    operator fun invoke(motorId: LoadId) =repository
        .getMotorFlow(motorId)
        .map { MotorPowerfactorDetail(it.id,it.code,it.powerfactor) }

}