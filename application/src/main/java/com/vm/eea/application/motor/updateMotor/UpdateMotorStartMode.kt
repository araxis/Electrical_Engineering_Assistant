package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.StartMode
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId


class UpdateMotorStartMode(private val repository: IMotorRepository) {
     suspend operator fun invoke(motorId:MotorId, value: StartMode) {
        repository.updateStartMode(motorId,value)
    }
}