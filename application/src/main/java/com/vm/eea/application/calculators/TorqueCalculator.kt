package com.vm.eea.application.calculators

import com.vm.eea.application.Power
import com.vm.eea.application.Speed
import com.vm.eea.application.Torque

class TorqueCalculator {
    operator fun invoke(power: Power,revolutions: Speed):Torque{
        val powerInWatt=(power to Power.Unit.W).value
        val value=(60*powerInWatt)/(2 * Math.PI * revolutions.value)
        return Torque(value)
    }
}