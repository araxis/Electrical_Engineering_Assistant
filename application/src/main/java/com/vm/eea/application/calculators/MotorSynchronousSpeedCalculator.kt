package com.vm.eea.application.calculators

import com.vm.eea.application.Frequency
import com.vm.eea.application.Speed


class MotorSynchronousSpeedCalculator {
    operator fun invoke(frequency: Frequency, numberOfPoles:Int):Speed{
        val frequencyInHz=(frequency to Frequency.Unit.Hz).value
        val value=60 *(frequencyInHz/(numberOfPoles/2))
        return Speed(value)
    }
}