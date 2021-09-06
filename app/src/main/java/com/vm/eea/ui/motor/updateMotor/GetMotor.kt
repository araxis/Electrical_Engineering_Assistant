package com.vm.eea.ui.motor.updateMotor

import com.vm.eea.domain.load.IMotorRepository
import com.vm.eea.domain.load.LoadId

class GetMotor(private val repository:IMotorRepository) {

    suspend operator fun invoke(id: LoadId)=repository.get(id)
}