package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import com.vm.eea.domain.Power
import com.vm.eea.domain.LoadId

data class MotorPowerDetails(val id: LoadId, val code:String, val power: Power)