package com.vm.eea.domain.load

import com.vm.eea.domain.Efficiency
import com.vm.eea.domain.LoadId

class UpdateMotorEfficiency(private val repository: IMotorRepository) {

    suspend operator fun invoke(motorId: LoadId,efficiency:Efficiency){
        repository.updateEfficiency(motorId,efficiency)
    }

}
