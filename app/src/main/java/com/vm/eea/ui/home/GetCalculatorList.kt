package com.vm.eea.ui.home

import com.vm.eea.ui.MotorCalculatorDestinations
import com.vm.eea.ui.MenuItem
import com.vm.eea.utilities.CosPhi


class GetCalculatorList{
    operator fun invoke(): List<MenuItem> {
        return listOf(
            MenuItem("Current") { MotorCalculatorDestinations.Current() },
            MenuItem("Voltage") { MotorCalculatorDestinations.Voltage() },
            MenuItem("Power") { MotorCalculatorDestinations.Power() },
            MenuItem(CosPhi) { MotorCalculatorDestinations.CosPhi() },
            MenuItem("Efficiency") { MotorCalculatorDestinations.Efficiency() },
            MenuItem("Speed") { MotorCalculatorDestinations.Speed() },
            MenuItem("Slip") { MotorCalculatorDestinations.Slip() },
            MenuItem("Torque") { MotorCalculatorDestinations.Torque() },
            MenuItem("Start mode") { MotorCalculatorDestinations.StartMode() },
            MenuItem("Circuit breaker") { MotorCalculatorDestinations.CircuitBreaker() },
            MenuItem("Over load relay") { MotorCalculatorDestinations.Guard() },
        )
    }
}

