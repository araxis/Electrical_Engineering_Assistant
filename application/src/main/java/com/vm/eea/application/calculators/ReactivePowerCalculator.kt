package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.sqrt

class ReactivePowerCalculator {

    operator fun invoke(voltage: Voltage, current: Current, cosPhi: CosPhi, system: PowerSystem): ReactivePower {
        val conf=when(system){
            PowerSystem.SinglePhase -> 1.0
            PowerSystem.TwoPhase -> 1.0
            PowerSystem.ThreePhase -> sqrt(3.0)
        }

        val baseVoltage=voltage to Voltage.Unit.V
        val baseCurrent=current to Current.Unit.A
        val sinPhi=cosPhi.sinPhi()
        val value=  baseVoltage.value * baseCurrent.value * conf * sinPhi
        return ReactivePower(value,ReactivePower.Unit.VAr)
    }

    operator fun invoke(power:Power,cosPhi: CosPhi):ReactivePower{
        val basePower=(power to Power.Unit.W).value
        val tanPhi=cosPhi.tanPhi()
        return ReactivePower(basePower * tanPhi,ReactivePower.Unit.VAr)
    }
}