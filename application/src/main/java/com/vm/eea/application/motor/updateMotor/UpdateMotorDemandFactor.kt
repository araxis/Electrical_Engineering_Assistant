package com.vm.eea.application.motor.updateMotor

import com.vm.eea.application.CosPhi
import com.vm.eea.application.motor.IGetMotorCosPhi
import com.vm.eea.application.motor.IMotorRepository
import com.vm.eea.application.motor.MotorId


class UpdateMotorDemandFactor(private val getMotorCosPhi: IGetMotorCosPhi,private val repository: IMotorRepository)  {

      suspend operator fun invoke(motorId: MotorId, demandFactor: CosPhi) {
          val cosPhi=getMotorCosPhi(motorId)
          if(cosPhi>demandFactor) throw Exception("demand factor must be greater than current cosPhi")
          repository.updateDemandFactor(motorId, demandFactor)
     }
}