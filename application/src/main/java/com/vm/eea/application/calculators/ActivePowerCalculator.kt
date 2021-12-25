package com.vm.eea.application.calculators

import com.vm.eea.application.*
import kotlin.math.sqrt

class ActivePowerCalculator {

    operator fun invoke(voltage: Voltage,current: Current,system: PowerSystem,cosPhi: CosPhi,efficiency: Efficiency):Power{
          val voltageInVolt=(voltage to Voltage.Unit.V).value
          val currentInAmpere= (current to Current.Unit.A).value
          val cosPhiValue=cosPhi.value
         val powerWattValue=system.conf * voltageInVolt * currentInAmpere * cosPhiValue *efficiency.percentage()
        return powerWattValue.W
    }

    operator fun invoke(voltage: Voltage,impedance: Impedance,system: PowerSystem,cosPhi: CosPhi):Power{
        val voltageInVolt=voltage to Voltage.Unit.V
        val voltagePow2=voltageInVolt.pow2().value
        val cosPhiValue=cosPhi.value
        val impedanceInOhm= impedance to Impedance.Unit.Ohm
        val powerInWatt=(system.conf * voltagePow2 * cosPhiValue)/impedanceInOhm.value
        return powerInWatt.W
    }

    operator fun invoke(powerSystem: PowerSystem,resistance: Resistance,current: Current):Power{
        val resistanceInOhm=(resistance to Resistance.Unit.Ohm).value
        val currentInAmpere=(current to Current.Unit.A).pow2().value
        val powerInWatt=powerSystem.conf * resistanceInOhm * currentInAmpere
        return powerInWatt.W

    }

    operator fun invoke(powerSystem: PowerSystem,impedance: Impedance,current: Current,cosPhi: CosPhi):Power{
        val resistanceInOhm=(impedance to Impedance.Unit.Ohm).value
        val currentInAmpere=(current to Current.Unit.A).pow2().value
        val powerInWatt=powerSystem.conf * resistanceInOhm * currentInAmpere * cosPhi.value
        return powerInWatt.W

    }

    operator fun invoke(apparentPower: ApparentPower,cosPhi: CosPhi):Power{
        val apparentPowerInVa=(apparentPower to ApparentPower.Unit.VA).value
        val powerInWatt= apparentPowerInVa * cosPhi.value
        return powerInWatt.W

    }

    operator fun invoke(reactivePower: ReactivePower,cosPhi: CosPhi):Power{
        val reactivePowerInVar=(reactivePower to ReactivePower.Unit.VAr ).value
        val powerInWatt= reactivePowerInVar / cosPhi.tanPhi()
        return powerInWatt.W

    }

    operator fun invoke(reactivePower: ReactivePower,apparentPower: ApparentPower):Power{
        val reactivePowerInVar=(reactivePower to ReactivePower.Unit.VAr ).pow2().value
        val apparentPowerInVa=(apparentPower to ApparentPower.Unit.VA ).pow2().value
        val powerInWatt= sqrt(apparentPowerInVa-reactivePowerInVar)
        return powerInWatt.W

    }

}