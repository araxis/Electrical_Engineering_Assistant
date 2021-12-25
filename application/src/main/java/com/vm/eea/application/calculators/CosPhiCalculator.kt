package com.vm.eea.application.calculators

import com.vm.eea.application.*

class CosPhiCalculator {

    operator fun invoke(power:Power,voltage:Voltage,current:Current,system: PowerSystem,efficiency: Efficiency):CosPhiResult{
        val powerInWatt=(power to Power.Unit.W).value
        val voltageInVolt=(voltage to Voltage.Unit.V).value
        val currentInAmpere=(current to Current.Unit.A).value
        val value=powerInWatt/(system.conf * voltageInVolt * currentInAmpere * efficiency.percentage())
              return try {
                  val cosPhi=CosPhi(value)
                  CosPhiResult.Ready(cosPhi)
              } catch (ex:Exception){
                  CosPhiResult.Error
              }

    }

    operator fun invoke(power:Power,voltage:Voltage,current:Current,system: PowerSystem):CosPhiResult{

        return invoke(power, voltage, current, system, Efficiency(1))
    }

    operator fun invoke(power:Power,apparentPower: ApparentPower):CosPhi{
        return power/apparentPower
    }

    operator fun invoke(reactivePower: ReactivePower,apparentPower: ApparentPower):CosPhi{
        return reactivePower/apparentPower
    }

    operator fun invoke(reactivePower: ReactivePower,activePower: Power):CosPhi{
        return reactivePower/activePower
    }
}

sealed class CosPhiResult{
    data class Ready(val value:CosPhi):CosPhiResult()
    object Error:CosPhiResult()
}