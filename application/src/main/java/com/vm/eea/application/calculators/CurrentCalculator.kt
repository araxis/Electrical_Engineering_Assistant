package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.sqrt

class CurrentCalculator {

    operator fun invoke(power: Power,
                        voltage: Voltage,
                        system: PowerSystem,
                        cosPhi: CosPhi,
                        efficiency: Efficiency
    ):Current {

        val conf = when (system) {
            PowerSystem.SinglePhase -> 1.0
            PowerSystem.TwoPhase -> 1.0
            PowerSystem.ThreePhase -> sqrt(3.0)


        }

        val powerInWatt = power to Power.Unit.W
        val voltageInVolt = voltage to Voltage.Unit.V
        val value= powerInWatt.value / (voltageInVolt.value * cosPhi.value * efficiency.percentage() * conf)
        return  Current(value,Current.Unit.A)

    }

    operator fun invoke(load: IPowerConsumer): Current {
       return invoke(load.power,load.voltage,load.system,load.cosPhi,load.efficiency)
    }

    operator fun invoke(loads:List<IPowerConsumer>):Current{
        return loads.sumOf { invoke(it).value } be Current.Unit.A
    }

}