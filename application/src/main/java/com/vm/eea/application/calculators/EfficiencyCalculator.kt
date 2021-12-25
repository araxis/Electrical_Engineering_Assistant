package com.vm.eea.application.calculators

import com.vm.eea.application.*


class EfficiencyCalculator {

    operator fun invoke(power:Power,voltage:Voltage,current:Current,system: PowerSystem,cosPhi: CosPhi):EfficiencyResult{
        val powerInWatt=(power to Power.Unit.W).value
        val voltageInVolt=(voltage to Voltage.Unit.V).value
        val currentInAmpere=(current to Current.Unit.A).value
        val value=powerInWatt/(system.conf * voltageInVolt * currentInAmpere * cosPhi.value)
        return try {
            val efficiency=Efficiency(value * 100)
            EfficiencyResult.Ready(efficiency)
        } catch (ex:Exception){
            EfficiencyResult.Error
        }

    }


}
sealed class EfficiencyResult{
    data class Ready(val value:Efficiency):EfficiencyResult()
    object Error:EfficiencyResult()
}