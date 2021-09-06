package com.vm.eea.ui.motor.updateMotor

import com.vm.eea.domain.load.IMotorRepository

class GetMotors(private val motorRepository: IMotorRepository) {

    operator fun invoke(projectId:Long)=motorRepository.get(projectId)
}