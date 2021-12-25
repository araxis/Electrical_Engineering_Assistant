package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.pow

class CapacitorCalculator {

    operator fun invoke(frequency:Frequency,voltage: Voltage,requiredReactivePower: ReactivePower):CapacitorValue{
        val frequencyInHertz=(frequency to Frequency.Unit.Hz) .value
        val voltageInVolt=(voltage to Voltage.Unit.V).value
        val kVar=(requiredReactivePower to ReactivePower.Unit.KVAr).value
        val microFarad= 159235000.0 * kVar / frequencyInHertz * voltageInVolt.pow(2)
        return CapacitorValue(microFarad,CapacitorValue.Unit.UF)
    }


}