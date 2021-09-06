package com.vm.eea.domain.load

import com.vm.eea.domain.PowerFactor

class UpdateMotorDemandFactor(private val repository: IMotorRepository) {

     suspend operator fun invoke(motorId: LoadId,value:PowerFactor) {
          repository.updateDemandFactor(motorId,value)
     }
}