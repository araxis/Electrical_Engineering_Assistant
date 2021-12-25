package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.sqrt

class ReactivePowerCalculator {

    operator fun invoke(voltage: Voltage, current: Current, cosPhi: CosPhi, system: PowerSystem): ReactivePower {


        val baseVoltage=voltage to Voltage.Unit.V
        val baseCurrent=current to Current.Unit.A
        val sinPhi=cosPhi.sinPhi()
        val value=  baseVoltage.value * baseCurrent.value * system.conf * sinPhi
        return ReactivePower(value,ReactivePower.Unit.VAr)
    }

    operator fun invoke(power:Power,cosPhi: CosPhi):ReactivePower{
        val basePower=(power to Power.Unit.W).value
        val tanPhi=cosPhi.tanPhi()
        return ReactivePower(basePower * tanPhi,ReactivePower.Unit.VAr)
    }

    operator fun invoke(power:ReactivePower,cosPhi: CosPhi):ReactivePower{
        val basePower=(power to ReactivePower.Unit.VAr).value
        val sinPhi=cosPhi.sinPhi()
        return ReactivePower(basePower * sinPhi,ReactivePower.Unit.VAr)
    }

    operator fun invoke(activePower:Power,apparentPower: ApparentPower):ReactivePower{
        val basePower=(activePower to Power.Unit.W).pow2().value
        val baseApparent=(apparentPower to ApparentPower.Unit.VA).pow2().value
        val value= sqrt(baseApparent-basePower)
        return ReactivePower(value,ReactivePower.Unit.VAr)
    }
}