package com.vm.eea.domain.defaultVoltage

import com.vm.eea.domain.PowerSystem
import com.vm.eea.domain.Voltage

class AddNewDefaultVoltage(private val defaultVoltageRepository: IDefaultVoltageRepository) {
    suspend operator fun  invoke(value: Voltage, system: PowerSystem){
        val defaultValue=DefaultVoltage(value,system,true,0)
        defaultVoltageRepository.insert(defaultValue)
    }
}