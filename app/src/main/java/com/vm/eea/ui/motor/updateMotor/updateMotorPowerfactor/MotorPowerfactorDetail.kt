package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import com.vm.eea.domain.PowerFactor
import com.vm.eea.domain.LoadId

data class MotorPowerfactorDetail(val motorId: LoadId, val code:String, val powerfactor: PowerFactor)