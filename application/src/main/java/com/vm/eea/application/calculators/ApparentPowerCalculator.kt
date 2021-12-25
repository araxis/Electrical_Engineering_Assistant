package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.pow
import kotlin.math.sqrt

class ApparentPowerCalculator {

    operator fun invoke(voltage: Voltage, current: Current, system: PowerSystem): ApparentPower {
            val conf=when(system){
                PowerSystem.SinglePhase -> 1.0
                PowerSystem.TwoPhase -> 1.0
                PowerSystem.ThreePhase -> sqrt(3.0)
            }
        val baseVoltage=(voltage to Voltage.Unit.V).value
        val baseCurrent=(current to Current.Unit.A).value

            return ApparentPower( baseVoltage *baseCurrent * conf,ApparentPower.Unit.VA)

    }

    operator fun invoke(power: Power, cosPhi: CosPhi):ApparentPower {
        val basePower=(power to Power.Unit.W).value
        return ApparentPower(basePower/cosPhi.value,ApparentPower.Unit.VA)
    }

    operator fun invoke(reactivePower: ReactivePower, cosPhi: CosPhi):ApparentPower {
        val baseReactivePower=(reactivePower to ReactivePower.Unit.VAr).value
        return ApparentPower(baseReactivePower/cosPhi.sinPhi(),ApparentPower.Unit.VA)
    }

    operator fun invoke(power:Power,reactivePower: ReactivePower):ApparentPower{
        val basePower=((power to Power.Unit.W)).value
        val baseReactive=((reactivePower to ReactivePower.Unit.VAr)).value
        val resultValue= sqrt(basePower.pow(2)+baseReactive.pow(2))
        return ApparentPower(resultValue,ApparentPower.Unit.VA)

    }
}