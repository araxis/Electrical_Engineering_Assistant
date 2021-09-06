package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import com.vm.eea.ui.models.SimpleMotor
import com.vm.eea.ui.models.SimplePanel

data class MotorFeederDetails(val motor:SimpleMotor, val availableFeeders:List<SimplePanel>)
