package com.vm.eea.domain.load

import com.vm.eea.domain.Power

class UpdateMotorPower(private val repository: IMotorRepository) {

    suspend operator fun invoke(motorId:LoadId,power:Power){
        repository.updatePower(motorId,power)
    }
}