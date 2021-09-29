package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.CosPhi
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId



class UpdateMotorCosPhi(private val repository: IMotorRepository)  {

     suspend operator fun invoke(motorId: MotorId, value: CosPhi){
        repository.updatePowerfactor(motorId,value)
    }
}