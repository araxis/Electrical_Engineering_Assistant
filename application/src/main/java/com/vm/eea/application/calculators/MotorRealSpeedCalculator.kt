package com.vm.eea.application.calculators

import com.vm.eea.application.Frequency
import com.vm.eea.application.SlipFactor
import com.vm.eea.application.Speed


class MotorRealSpeedCalculator {
    operator fun invoke(frequency: Frequency, numberOfPoles:Int, slipFactor: SlipFactor):Speed{
        val frequencyInHz=(frequency to Frequency.Unit.Hz).value
        val value=(60 * frequencyInHz * (1-slipFactor.zeroToOne()))/(numberOfPoles/2)
        return Speed(value)
    }
}