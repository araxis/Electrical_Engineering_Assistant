package com.vm.eea.application.calculators

import com.vm.eea.application.*

class VoltageCalculator {
    operator fun invoke(power: Power,current:Current,cosPhi: CosPhi,efficiency: Efficiency,system: PowerSystem):Voltage{
        val powerInWatt=(power to Power.Unit.W).value
        val currentInAmpere=(current to Current.Unit.A).value
        val volTageInVolt=powerInWatt/(system.conf * currentInAmpere * cosPhi.value * efficiency.percentage())
        return volTageInVolt.v

    }
}