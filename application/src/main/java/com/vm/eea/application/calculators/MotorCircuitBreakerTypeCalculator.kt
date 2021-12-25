package com.vm.eea.application.calculators

import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionDeviceType

class MotorCircuitBreakerTypeCalculator(private val currentCalculator: CurrentCalculator) {

     operator fun invoke(current: Current): ProtectionDeviceType {
        if (current < 63.A) {
            return ProtectionDeviceType.Tmb
        }
        if (current >= 63.A && current < 1600.A) {
            return ProtectionDeviceType.Mccb
        }
        return ProtectionDeviceType.Acb
    }
     operator fun invoke(voltage: Voltage, power: Power, system: PowerSystem, cosPhi: CosPhi, efficiency: Efficiency): ProtectionDeviceType {
        val current=currentCalculator(voltage, power, system, cosPhi, efficiency)
        return invoke(current)
    }

}

