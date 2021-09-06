package com.vm.eea.domain.load

import com.vm.eea.domain.PowerFactor

class UpdateMotorPowerfactor(private val repository: IMotorRepository) {

    suspend operator fun invoke(motorId:LoadId,value: PowerFactor){
        repository.updatePowerfactor(motorId,value)
    }
}