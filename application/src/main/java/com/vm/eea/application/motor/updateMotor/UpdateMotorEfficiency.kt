package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.Efficiency
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId

class UpdateMotorEfficiency(private val repository: IMotorRepository)  {

     suspend operator fun invoke(motorId: MotorId, efficiency: Efficiency){
        repository.updateEfficiency(motorId,efficiency)
    }

}
