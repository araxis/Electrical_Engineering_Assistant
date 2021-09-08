package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import com.vm.eea.domain.Efficiency
import com.vm.eea.domain.LoadId
import com.vm.eea.domain.load.IMotorRepository
import kotlinx.coroutines.flow.map

class GetMotorEfficiency(private val repository: IMotorRepository) {

    operator fun invoke(motorId:LoadId) =repository.getMotorFlow(motorId).map {
        MotorEfficiency(it.id,it.code,it.efficiency)
    }

  data class MotorEfficiency(val id: LoadId,val code:String,val efficiency: Efficiency)
}