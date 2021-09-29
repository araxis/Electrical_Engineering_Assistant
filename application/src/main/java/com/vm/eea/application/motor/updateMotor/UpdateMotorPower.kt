package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.Power
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId

class UpdateMotorPower(private val repository: IMotorRepository)  {
     suspend operator fun invoke(motorId: MotorId, power: Power){
        repository.updatePower(motorId,power)
    }
}